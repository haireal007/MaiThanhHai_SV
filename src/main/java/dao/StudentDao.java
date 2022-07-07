package dao;

import connect_MySQL.Connect_MySQL;
import model.ClassStudent;
import model.Student;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements CRUD<Student> {
    ClassStudentDao classStudentDao = new ClassStudentDao();

    @Override
    public List<Student> getAll() {
        String sql = "select * from student";
        List<Student> students = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date date = resultSet.getDate("dateOfBirth");
                LocalDate dateOfBirth = date.toLocalDate();
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                ClassStudent classStudent = classStudentDao.findById(resultSet.getInt("idClass"));
                students.add(new Student(id, name, dateOfBirth, address, email, phoneNumber, classStudent));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }
    public List<Student> getAllByName(String name) {
        String sql = "select * from student where name like concat('%',?,'%')";
        List<Student> students = new ArrayList<>();
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameS = resultSet.getString("name");
                Date date = resultSet.getDate("dateOfBirth");
                LocalDate dateOfBirth = date.toLocalDate();
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                ClassStudent classStudent = classStudentDao.findById(resultSet.getInt("idClass"));
                students.add(new Student(id, nameS, dateOfBirth, address, email, phoneNumber, classStudent));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean create(Student student) {
        String sql = "insert into student value (?,?,?,?,?,?,?)";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, String.valueOf(student.getDateOfBirth()));
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setString(6, student.getPhoneNumber());
            preparedStatement.setInt(7, student.getClassStudent().getId());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean edit(int id, Student student) {
        String sql = "UPDATE student SET name = ?,dateOfBirth = ?, " +
                "address = ?,email = ?,phoneNumber = ?, idClass=? WHERE (id = ?)";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(7, student.getId());
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, String.valueOf(student.getDateOfBirth()));
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getEmail());
            preparedStatement.setString(5, student.getPhoneNumber());
            preparedStatement.setInt(6, student.getClassStudent().getId());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from student WHERE id = ?";
        try (Connection connection = Connect_MySQL.getConnect()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public Student findById(int id) {
        String sql = "select * from student where id = " + id;
        try (Connection connection = Connect_MySQL.getConnect()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            resultSet.next();
            int idS = resultSet.getInt("id");
            String name = resultSet.getString("name");
            LocalDate dateOfBirth = LocalDate.parse(resultSet.getString("dateOfBirth"));
            String address = resultSet.getString("address");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phoneNumber");
            ClassStudent classStudent = classStudentDao.findById(resultSet.getInt("idClass"));

            return new Student(idS, name, dateOfBirth, address, email, phoneNumber, classStudent);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
