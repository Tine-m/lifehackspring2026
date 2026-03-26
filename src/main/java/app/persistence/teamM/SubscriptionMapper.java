package app.persistence.teamM;

import app.entities.teamM.Subscription;
import app.persistence.ConnectionPool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubscriptionMapper {
    private ConnectionPool connectionPool;

    public SubscriptionMapper(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void addSubscription(Subscription subscription) {
        String sql = "insert into subscriptions (name, price, due_date) values (?, ?, ?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, subscription.getName());
            preparedStatement.setDouble(2, subscription.getPrice());
            preparedStatement.setDate(3, Date.valueOf(subscription.getDueDate()));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Could not add subscription", e);
        }
    }

    public ArrayList<Subscription> getAllSubscriptions() {
        ArrayList<Subscription> subscriptions = new ArrayList<>();
        String sql = "select name, price, due_date from subscriptions";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                String dueDate = resultSet.getDate("due_date").toString();

                subscriptions.add(new Subscription(name, price, dueDate));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Could not fetch subscriptions", e);
        }

        return subscriptions;
    }

    public void deleteSubscriptionByName(String name) {
        String sql = "delete from subscriptions where name = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Could not delete subscription", e);
        }
    }
}