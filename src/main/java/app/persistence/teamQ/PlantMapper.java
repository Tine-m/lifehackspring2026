    package app.persistence.teamQ;

    import app.entities.teamQ.Plant;
    import app.persistence.ConnectionPool;

    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;

    public class PlantMapper {
        public static Plant getPlant(long plant_id, ConnectionPool connectionPool) throws app.exceptions.common.DatabaseException {
            String sql = "select * from plants where plant_id=?";
            try (
                    Connection connection = connectionPool.getConnection();
                    PreparedStatement ps = connection.prepareStatement(sql)
            )
            {
                String plantName = "unknown";
                int health = 0;
                int lightHappiness = 0;
                int waterLevel = 0;

                ps.setString(2, plantName);
                ps.setInt(3, health);
                ps.setInt(4, lightHappiness);
                ps.setInt(5, waterLevel);

                ResultSet rs = ps.executeQuery();
                if (rs.next())
                {
                    return new Plant(plantName, health, lightHappiness, waterLevel);
                } else
                {
                    throw new app.exceptions.common.DatabaseException("Kunne ikke finde plante, prøv igen");
                }
            }
            catch (SQLException e)
            {
                throw new app.exceptions.common.DatabaseException("DB fejl", e.getMessage());
            }
        }

        public static void addPlantToUser(String username, String password, ConnectionPool connectionPool) throws app.exceptions.common.DatabaseException
        {
            String sql = "insert into users (username, password) values (?,?)";

            try (
                    Connection connection = connectionPool.getConnection();
                    PreparedStatement ps = connection.prepareStatement(sql)
            )
            {
                ps.setString(1, username);
                ps.setString(2, password);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 1)
                {
                    throw new app.exceptions.common.DatabaseException("Fejl ved oprettelse af ny bruger");
                }
            }
            catch (SQLException e)
            {
                String msg = "Der er sket en fejl. Prøv igen";
                if (e.getMessage().startsWith("ERROR: duplicate key value "))
                {
                    msg = "Brugernavnet findes allerede. Vælg et andet";
                }
                throw new app.exceptions.common.DatabaseException(msg, e.getMessage());
            }
        }
    }
