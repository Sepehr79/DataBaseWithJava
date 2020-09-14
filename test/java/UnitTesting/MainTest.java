package UnitTesting;

import MysqlTutorials.DataBase.MysqlDataBase;
import MysqlTutorials.DataBase.Student;
import org.junit.Assert;
import org.junit.Test;


public class MainTest {
    
    @Test
    public void addStudentTest(){
        Student student = new Student("mohammad", "karimi", 23, 98211);
        MysqlDataBase.addStudent(student);
        Student databaseStudent = MysqlDataBase.getStudentById(98211);

        Assert.assertEquals("mohammad", databaseStudent.getName());
        Assert.assertEquals("karimi", databaseStudent.getLastName());
        Assert.assertEquals(23, databaseStudent.getAge());
        Assert.assertEquals(98211, databaseStudent.getId());

    }

    @Test
    public void emptyTableTest(){
        MysqlDataBase.deleteAllStudents();
        Assert.assertTrue(MysqlDataBase.getStudents().isEmpty());
    }

    @Test
    public void testModifyStudent(){
        MysqlDataBase.modifyStudentById(98211, MysqlDataBase.AGE, 20);
        Student student = MysqlDataBase.getStudentById(98211);
        Assert.assertTrue(student.getAge() == 20);
    }


}
