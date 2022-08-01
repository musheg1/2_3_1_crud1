package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    // Hiber


    private static final String URL = "jdbc:mysql://localhost:3306/taskhibernateschema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "trapshit1997";
    private static SessionFactory sessionFactory;
    private static SessionFactory sessionFactoryToClose;

    public static SessionFactory getSessionFactoryToClose() {
        return sessionFactoryToClose;
    }

    public static SessionFactory getFact() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "false");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Connection OK!");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection ERROR");
            }
        }
        return sessionFactory;
    }


    //    соединение JDBC
//    private static final String URL = "jdbc:mysql://localhost:3306/taskjdbcschema";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "trapshit1997";
//
    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection OK");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }

}




