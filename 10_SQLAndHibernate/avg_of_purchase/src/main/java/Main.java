import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url ="jdbc:mysql://localhost:3306/learn?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "cuafbu5k3a";
        try {
            Connection connection = DriverManager.getConnection(url,user,pass);
            Statement statement = connection.createStatement(); 
            ResultSet rs = statement.executeQuery("SELECT DISTINCT Courses.name, ((MONTH(x.maxdate) - MONTH(y.mindate))/count_purch) as start FROM Subscriptions, " +
                    "Courses, (SELECT MAX(subscription_date) AS maxdate, " +
                    "course_id AS id_stop FROM Subscriptions GROUP BY course_id) x, (SELECT MIN(subscription_date) AS mindate, " +
                    "course_id AS id_start FROM Subscriptions GROUP BY course_id) y, (SELECT COUNT(course_id) AS count_purch, " +
                    "course_id AS id_count FROM Subscriptions GROUP BY course_id)z WHERE Subscriptions.course_id = id_stop " +
                    "AND Subscriptions.course_id = id_start AND Subscriptions.course_id = id_count " +
                    "AND Courses.id = id_stop ORDER BY Subscriptions.course_id;");
            while(rs.next()){
                String course = rs.getString("name");
                int purchase = rs.getInt("start");
                System.out.println(course + " - " + purchase);
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
