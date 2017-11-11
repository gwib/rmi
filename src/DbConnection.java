import java.sql.*;


public class DbConnection {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/details";

    //  Database credentials
    //Todo: might have to change these to user="root" pass="" as we want root access to db
    static final String USER = "myuser";
    static final String PASS = "password";

    public Connection connectToMysql(){
        Connection conn = null;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connection to DB established");
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return conn;
        }catch (SQLException e) {
            e.printStackTrace();
            return conn;
        }
    }
}