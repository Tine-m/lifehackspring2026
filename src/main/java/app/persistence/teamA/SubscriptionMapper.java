package app.persistence.teamA;
import app.entities.teamA.Subscription;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubscriptionMapper {

    public static ArrayList<Subscription> getAllSubscriptionInfo(int userId, app.persistence.ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT * FROM teama_subscriptions WHERE user_id=?";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            ArrayList<Subscription> subscriptions = new ArrayList<>();
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int subId = rs.getInt("subscription_id");
                String subName = rs.getString("subscription_name");
                double subCost = rs.getDouble("subscription_cost");
                int subUsage = rs.getInt("usage_amount");
                String subCategory = rs.getString("category");
                subscriptions.add(new Subscription(subId, subName, subCost, subUsage, subCategory));
            }
            return subscriptions;
        } catch (SQLException e) {
            throw new DatabaseException("No subscriptions found attached to you", e.getMessage());
        }
    }

    public static void createSubscription(String subName, double subCost, int subUsage, String subCategory, int userId, app.persistence.ConnectionPool connectionPool) throws DatabaseException {
        String sql = "INSERT INTO teama_subscriptions (subscription_name, subscription_cost, usage_amount, category, user_id) values (?,?,?,?,?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setString(1, subName);
            ps.setDouble(2, subCost);
            ps.setInt(3, subUsage);
            ps.setString(4, subCategory);
            ps.setInt(5, userId);

           int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1){
                throw new DatabaseException("Missing details in subscription");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error creating subscription" + e.getMessage());
        }
    }


    public static void deleteSubscription(int userId, int subId, ConnectionPool connectionPool) throws DatabaseException{
        String sql = "DELETE FROM teama_subscriptions WHERE user_id = ? AND subscription_id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);) {
            ps.setInt(1, userId);
            ps.setInt(2, subId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1){
                throw new DatabaseException("Could not find subscription");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting subscription", e.getMessage());
        }
    }

    public static ArrayList<Subscription> getAllSubscriptions(ConnectionPool connectionPool)throws DatabaseException{
        String sql = "SELECT * FROM teama_subscriptions JOIN teama_users USING (user_id)";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            ArrayList<Subscription> subscriptions = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int subId = rs.getInt("subscription_id");
                String subName = rs.getString("subscription_name");
                double subCost = rs.getDouble("subscription_cost");
                int subUsage = rs.getInt("usage_amount");
                String subCategory = rs.getString("category");
                int userID = rs.getInt("user_id");
                String username = rs.getString("username");
                subscriptions.add(new Subscription(subId, subName, subCost, subUsage, subCategory, userID, username));
            }
            return subscriptions;
        } catch (SQLException e) {
            throw new DatabaseException("No subscriptions found attached to you", e.getMessage());
        }
    }
}
