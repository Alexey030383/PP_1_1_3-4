package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Statement statement;

    public UserDaoJDBCImpl() {
        try {
            this.statement = new Util().getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsersTable() {
        try {
            statement.execute("""
                    CREATE TABLE `pp1134`.`users` (
                      `id` INT AUTO_INCREMENT,
                      `name` VARCHAR(45),
                      `lastName` VARCHAR(45),
                      `age` INT,
                      PRIMARY KEY (`id`))""");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            statement.execute("DROP TABLE pp1134.users;");
        } catch (SQLException ignored) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = statement.getConnection().
                    prepareStatement("insert into pp1134.users(name, lastName, age) values(?, ?, ?);");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            statement.execute("DELETE FROM pp1134.users where id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = statement.getConnection().prepareStatement("SELECT * FROM pp1134.users;");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                Byte age = resultSet.getByte(4);
                User user = new User(name, lastName, age);
                user.setId(id);
                usersList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(usersList);
        return usersList;
    }

    public void cleanUsersTable() {
        try {
            statement.execute("DELETE FROM pp1134.users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            statement.getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}