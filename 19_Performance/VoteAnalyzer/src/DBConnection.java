import java.sql.*;

public class DBConnection {

    private static Connection connection;
//    private static String dbName = "learn";
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
//                        "PRIMARY KEY(id), " +
//                        "UNIQUE KEY name_date(birthDate ,name(50)))");

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
  /*  public static void executeMultiInsert(StringBuilder insertQuery) throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) " +
                "VALUES" + insertQuery.toString() +
                "ON DUPLICATE KEY UPDATE count = count + 1";
        DBConnection.getConnection().createStatement().execute(sql);
    }*/

    public static void executeUpdate(String name, int count, String birthDate) throws SQLException {
        String sql = "update voter_count set count=? where name=? AND birthDate=?";
        PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,count);
        preparedStatement.setString(2,name);
        preparedStatement.setString(3,birthDate);
        preparedStatement.executeUpdate();

    }

/*    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
    }*/
}
