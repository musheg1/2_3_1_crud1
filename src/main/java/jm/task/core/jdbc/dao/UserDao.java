package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {

    void create(User user);

    List<User> getAllUsers();

    void update(User user);

    void delete(long id);

    User findUserById(long id);

    void dropTable();
    void createTable();
}
