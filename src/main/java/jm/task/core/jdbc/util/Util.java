package jm.task.core.jdbc.util;
import java.sql.*;
public class Util {
    // реализуйте настройку соеденения с БД

    private static final String URL = "jdbc:mysql://localhost:3306/taskjdbcschema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "trapshit1997";



    public Connection getConnection () {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection OK");
        } catch ( SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }

}


