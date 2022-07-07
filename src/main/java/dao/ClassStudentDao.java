package dao;

import connect_MySQL.Connect_MySQL;
import model.ClassStudent;
import model.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClassStudentDao implements CRUD<ClassStudent> {
    @Override
    public List<ClassStudent> getAll() {
        String sql = "select * from ClassStudent";
        List<ClassStudent> classStudents = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int idClass = resultSet.getInt(1);
                String nameClass = resultSet.getString(2);
                classStudents.add(new ClassStudent(idClass, nameClass));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return classStudents;
    }


    @Override
    public boolean create(ClassStudent classStudent) {
        return false;
    }

    @Override
    public boolean edit(int id, ClassStudent classStudent) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public ClassStudent findById(int id) {
        String sql = "select * from classstudent where idClass = ?";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            int idClass = resultSet.getInt(1);
            String nameClass = resultSet.getString(2);
            ClassStudent classStudent = new ClassStudent(idClass, nameClass);
            return classStudent;


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
