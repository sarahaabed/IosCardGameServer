/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.User;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed
 */
public class UserDao {

    private static UserDao INSTANCE = new UserDao();
    private Connection connection;

    private UserDao() {

        connection = new DBConnection().getConnection();

    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    public boolean insertUser(User user) {

        // prepare query
        String query = "INSERT INTO user_table VALUES(?,?,?)";

        // inserted indicator
        boolean inserted = false;

        try {
            // create prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPassword());

            // execute insert
            statement.executeUpdate();
            System.out.println("from insertion methode..inserted success");
            inserted = true;

        } catch (SQLException ex) {
            System.out.println("from insertion exception .");
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return inserted;
    }

    public User findUserByEmail(String email) {

        // prepare query
        String query = "SELECT * FROM user_table WHERE email = ?";

        // create user 
        User user = new User();

        try {

            // create prepared statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            // excute query
            ResultSet result = statement.executeQuery();

            // check if there is rows in result
            if (result != null && result.next()) {

                user.setEmail(result.getString("Email"));
                user.setName(result.getString("Name"));
                user.setPassword(result.getString("Password"));
                user.setScore(result.getInt("Score"));
                return user;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
