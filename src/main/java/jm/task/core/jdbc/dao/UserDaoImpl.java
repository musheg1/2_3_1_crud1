package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findUserById(id));
    }

    @Override
    public User findUserById(long id) {
        System.out.println(entityManager.find(User.class, id));
        return entityManager.find(User.class, id);
    }

    @Override
    public void dropTable() {
        String sql = "DROP TABLE IF EXISTS users ;";
        entityManager.createQuery(sql);
    }

    @Override
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45), " +
                "lastname VARCHAR(45), age INT,  PRIMARY KEY (`id`)) ;";
        entityManager.createQuery(sql);
    }
}
