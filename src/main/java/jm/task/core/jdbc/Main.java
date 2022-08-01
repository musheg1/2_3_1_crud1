package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import com.mysql.cj.xdevapi.SessionFactory;
import jm.task.core.jdbc.util.Util;


public class Main {

    private final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        userDaoHibernate.createUsersTable();

        userDaoHibernate.saveUser("Шерлок", "Холмс", (byte) 33);
        userDaoHibernate.saveUser("Майкрофт", "Холмс", (byte) 40);
        userDaoHibernate.saveUser("Эвр", "Холмс", (byte) 32);
        userDaoHibernate.saveUser("Джон", "Ватсон", (byte) 35);

        userDaoHibernate.removeUserById(2);

        userDaoHibernate.getAllUsers();

        userDaoHibernate.cleanUsersTable();

        userDaoHibernate.dropUsersTable();

    }

}

