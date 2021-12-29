import java.sql.*;

public class DBConnection {

    private static Connection connection;
    private static String dbUser = "root";
    private static String dbPass = "cuafbu5k3a";
    private static String url = "jdbc:mysql://localhost:3306/learn?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false&serverTimezone=UTC";

    public static Connection getConnection() {
        if (connection == null) {
            try {

                connection = DriverManager.getConnection(url, dbUser, dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert(StringBuilder insertQuery) throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
                "VALUES" + insertQuery.toString();
        DBConnection.getConnection().createStatement().execute(sql);
    }

}
