package com.siddhant.class_management.dao;

import com.siddhant.class_management.model.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentDao {

    public boolean addStudent(Student student) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/siddhant","root","root");
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate("INSERT INTO Student VALUES ('"+student.getName()+"',"+student.getRollNo()+" , "+student.getStd()+", "+student.getAge()+", '"+student.getFather()+"', '"+student.getMother()+"', '"+student.getCity()+"')");

            return resultSet==1?
                    true: false;

        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Student getStudentByRollNo(int rollNo) {
        Student student = null;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/siddhant","root","root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from Student where roll_no=" + rollNo);

            if(resultSet.next()) {
                student.setName(resultSet.getString(1));
                student.setRollNo(resultSet.getInt(2));
                student.setStd(resultSet.getInt(3));
                student.setAge(resultSet.getInt(4));
                student.setFather(resultSet.getString(5));
                student.setMother(resultSet.getString(6));
                student.setCity(resultSet.getString(7));
            }
            return student;
        } catch(Exception e){
            e.printStackTrace();
            return student;
        }
    }

    public List<Student> getAllStudent() {
        List<Student> listOfStudents=new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/siddhant","root","root");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("Select * from Student");

            while (resultSet.next()) {
                Student student = new Student();
                student.setName(resultSet.getString(1));
                student.setRollNo(resultSet.getInt(2));
                student.setStd(resultSet.getInt(3));
                student.setAge(resultSet.getInt(4));
                student.setFather(resultSet.getString(5));
                student.setMother(resultSet.getString(6));
                student.setCity(resultSet.getString(7));
                listOfStudents.add(student);
            }
            return listOfStudents;
        } catch(Exception e){
            e.printStackTrace();
            return listOfStudents;
        }

    }

    public boolean deleteStudentById(int rollNo) {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/siddhant", "root", "root");){
            Statement statement = connection.createStatement();
            int i = statement.executeUpdate("DELETE from Student where roll_no=" + rollNo);

            return i == 1 ? true : false;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    //vsaini: method will return unDeplicated email array
    private static String[] unDuplicateEmailArray(String[] currentArray, String[] arrayA, String[] arrayB) {
        String unDuplicatedArrayString = "";

        for (String email : currentArray) {
            if (!isEmailExist(arrayA, arrayB, email)) {
                if (unDuplicatedArrayString.length() > 1) {
                    unDuplicatedArrayString = unDuplicatedArrayString + "," + email;
                } else {
                    unDuplicatedArrayString = email;
                }
            }
        }
        return unDuplicatedArrayString.length() > 2 ?
                unDuplicatedArrayString.split(",") : new String[0];
    }
    //vsaini: method will return true is cc, bcc or to email contains email
    private static boolean isEmailExist(String[] arrayA, String[] arrayB, String email) {
        return (Arrays.stream(arrayA).anyMatch(email::equals)
                || Arrays.stream(arrayB).anyMatch(email::equals));
    }

    public static void main2(String[] args) {
        List<String> strings = Arrays.asList("vinit", "saini");
        System.out.println(strings.toString().replace("[","")
                .replace("]","").replace(", ",","));
    }
}
