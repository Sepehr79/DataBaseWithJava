package MysqlTutorials.DataBase;

import java.sql.*;
import java.util.ArrayList;

public class MysqlDataBase {
    private static Connection connection;
    private static Statement statement;

    public static final int NAME = 0;
    public static final int LASTNAME = 1;
    public static final int AGE = 2;
    public static final int ID = 3;

    private MysqlDataBase(){ }

    private static void openConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JDBC", "root", "msprm9731");
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void closeConnection(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static boolean isDuplicateStudent(Student student){
        ArrayList<Student> students = getStudents();
        for (Student std:students){
            if ((std.getName().equals(student.getName()) && std.getAge() == student.getAge() &&
                    std.getLastName().equals(student.getLastName())) || student.getId() == std.getId())
                return true;
        }
        return false;
    }

    private static void modifyStudentNameById(int id, String name){
        openConnection();

        try {
            statement.executeUpdate(String.format("update students set name = '%s' where id = %d", name, id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        closeConnection();
    }

    private static void modifyStudentLastNameById(int id, String lastName){
        openConnection();

        try {
            statement.executeUpdate(String.format("update students set lastName = '%s' where id = %d", lastName, id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        closeConnection();
    }

    private static void modifyStudentAgeById(int id, int age){
        openConnection();

        try {
            statement.executeUpdate(String.format("update students set age = %d where id = %d", age, id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        closeConnection();
    }

    public static ArrayList<Student> getStudents(){
        openConnection();

        ArrayList<Student> students = new ArrayList<Student>();
        try {
            ResultSet resultSet = statement.executeQuery("select * from students");

            while (resultSet.next()){
                students.add(new Student(resultSet.getString("name"), resultSet.getString("lastName"),
                        resultSet.getInt("age"), resultSet.getInt("id")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        closeConnection();
        return students;
    }

    public static Student getStudentById(int id){
        openConnection();

        Student student = null;
        try {
            ResultSet resultSet = statement.executeQuery(String.format("select * from students where id = %d", id));
            while (resultSet.next()){
                student = new Student(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getInt("age"), resultSet.getInt("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        closeConnection();
        return student;

    }

    public static Student getLastStudent(){
        openConnection();

        Student student = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students ORDER BY id DESC LIMIT 1;");
            while (resultSet.next()){
                student = new Student(resultSet.getString("name"), resultSet.getString("lastName"), resultSet.getInt("age"), resultSet.getInt("id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        closeConnection();
        return student;
    }

    public static void addStudent(Student student){
        if(!isDuplicateStudent(student)) {
            openConnection();
            try {
                statement.executeUpdate(String.format("INSERT into students (name, lastName, age, id)values ('%s', '%s', %d, %d)",
                        student.getName(), student.getLastName(), student.getAge(), student.getId()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            closeConnection();
        }else {
            System.out.println("Duplicate student cant add!");
        }
    }

    public static void deleteStudentById(int id){
        openConnection();

        try {
            statement.executeUpdate(String.format("delete from students where id = %d", id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        closeConnection();
    }

    public static void deleteAllStudents(){
        openConnection();

        try {
            statement.executeUpdate("delete from students");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        closeConnection();
    }

    public static void modifyStudentById(int id, int type, String input){
        if (type == NAME){
            modifyStudentNameById(id, input);
        }if (type == LASTNAME){
            modifyStudentLastNameById(id, input);
        }
    }

    public static void modifyStudentById(int id, int type, int input){
        if (type == AGE){
            modifyStudentAgeById(id, input);
        }
    }
}
