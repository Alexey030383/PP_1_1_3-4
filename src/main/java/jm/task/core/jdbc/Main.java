package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alexey", "Baryshev", (byte) 40);
        userService.saveUser("Vitaly", "Baryshev", (byte) 11);
        userService.saveUser("Maxim", "Baryshev", (byte) 2);
        userService.saveUser("Anastasya", "Baryshev", (byte) 37);
        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}