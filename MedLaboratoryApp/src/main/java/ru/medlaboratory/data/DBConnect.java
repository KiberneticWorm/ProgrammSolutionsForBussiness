package ru.medlaboratory.data;

import ru.medlaboratory.data.entity.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnect {
    private Connection connection;
    public DBConnect() {
        try {

            Driver driver = new com.mysql.cj.jdbc.Driver();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/medlaboratory_db?useUnicode=true&serverTimezone=UTC", "root", "12345");
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void finalize() {
        try {
            connection.close();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    public List<User> getUsers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("select * from users");
        List<User> users = new ArrayList<>();
        while (set.next()) {
            User user = getUser(set);
            users.add(user);
        }
        return users;
    }

    public User getUser(ResultSet set) throws SQLException {
        return new User(
                set.getInt("user_id"),
                set.getString("username"),
                set.getString("password"),
                set.getString("fio"),
                set.getInt("age"),
                set.getString("type user"),
                set.getString("image_path")
        );
    }


    public void setUser(User user) throws SQLException {
        String query = "INSERT INTO `medlaboratory_db`.`users` (`user_id`, `username`, `password`, `fio`, `age`, `type user`, `image_path`) VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, user.getUserId() + 1);
        statement.setString(2, user.getUserName());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getFio());
        statement.setInt(5, user.getAge());
        statement.setString(6, user.getTypeUser());
        statement.setString(7, user.getImagePath());

        statement.executeQuery();
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        try {
            String query = "select * from users where username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);

            ResultSet set = statement.executeQuery();
            if (set.next() && set.getString("password").equals(password)) {
                return getUser(set);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return null;
    }
}
