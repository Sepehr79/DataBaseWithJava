package src.DataBaseWithJava.main.MysqlTutorials.test;

import org.junit.*;
import src.DataBaseWithJava.main.MysqlTutorials.DataBase.IDataBase;
import src.DataBaseWithJava.main.MysqlTutorials.DataBase.MysqlDataBase;
import src.DataBaseWithJava.main.MysqlTutorials.DataBase.Student;


public class TestDataBases {
    @Test
    public void testDeleteStudents(){
        MysqlDataBase.deleteAllStudents();
    }

    @Test
    public void testAddStudent(){
        Student student = new Student("sepehr", "mollaei", 20, 1273314018);
        MysqlDataBase.addStudent(student);
    }

    @Test
    public void testModifyStudentById(){
        MysqlDataBase.modifyStudentById(1273314018, IDataBase.NAME, "hosein");
        Student student = MysqlDataBase.getStudentById(1273314018);
        Assert.assertEquals("hosein", student.getName());
    }


}
