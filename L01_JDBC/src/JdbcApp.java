import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcApp {

    private static final String url = "jdbc:postgresql://switchback.proxy.rlwy.net:16551/railway";
    private static final String user = "postgres";
    private static final String password = "DYeWkgHrkNHNUWzbHrJYxYlTCGljTbyb";
    private static final String driver = "org.postgresql.Driver";

    private static Connection connection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Driver Not Found");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = connection();
        System.out.println(connection.getMetaData().getDatabaseProductName());
        connection.close();


    }
}
