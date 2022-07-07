package dao;

import connect_MySQL.Connect_MySQL;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {
    String sql = "select * from account where username = ? and password =?";

    public Account getAccount(String username, String password) {
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            int id = resultSet.getInt("id");
            String name = resultSet.getString("username");
            String pass = resultSet.getString("password");
            String role = resultSet.getString("role");

            return new Account(id, name, password, role);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
