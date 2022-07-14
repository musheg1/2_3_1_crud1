package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();

        userDaoJDBC.saveUser("Шерлок", "Холмс", (byte) 33);
        userDaoJDBC.saveUser("Майкрофт", "Холмс", (byte) 40);
        userDaoJDBC.saveUser("Эвр", "Холмс", (byte) 32);
        userDaoJDBC.saveUser("Джон", "Ватсон", (byte) 35);

        userDaoJDBC.removeUserById(2);

        userDaoJDBC.getAllUsers();

        userDaoJDBC.cleanUsersTable();

        userDaoJDBC.dropUsersTable();

    }

}

