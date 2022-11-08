package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.model.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public User findUserById(long id) {
        return userDao.findUserById(id);
    }

    @Override
    public void dropTable() {
        userDao.dropTable();
    }

    @Override
    public void createTable() {
        userDao.createTable();
    }
}

