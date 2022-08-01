package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45), " +
                "lastname VARCHAR(45), age INT,  PRIMARY KEY (`id`)) ;";
        try (Session session = Util.getFact().getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users ;";
        try (Session session = Util.getFact().getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getFact().getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.save(new User(name, lastName, age));
            transaction.commit();

        }

    }
    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getFact().getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(session.get(User.class, id));
            transaction.commit();

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = Util.getFact().getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            users= session.createQuery("from User",User.class).list();
            transaction.commit();

        }
        return users;
    }


    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (Session session = Util.getFact().getCurrentSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.createSQLQuery(sql).executeUpdate();
        }
    }
}
