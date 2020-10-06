package src.DataBaseWithJava.main.MysqlTutorials.test;

import org.junit.*;
import src.DataBaseWithJava.main.MysqlTutorials.DataBase.MysqlDataBase;


public class TestDataBases {
    @Test
    public void testDeleteStudents(){
        MysqlDataBase.deleteAllStudents();
    }
}
