package src.DataBaseWithJava.main.MysqlTutorials.DataBase;

import java.sql.*;
// name, lastName, age, gender, id
public class SQLiteDataBase {
    public static void main(String[] args) {

        try {
            //Class.forName("org.sqlite.jdbc3");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:Student");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from student");
            while (resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
