package com.siddhant.class_management.service;

import com.siddhant.class_management.dao.StudentDao;
import com.siddhant.class_management.model.Student;

import java.sql.*;
import java.util.*;

public class StudentService {
    StudentDao studentDao = new StudentDao();

    private Student addStudent() {
        Student student = new Student();

        Scanner userInput = new Scanner(System.in);

        System.out.println("Enter your name : ");
        student.setName(userInput.next());

        System.out.println("Enter your Roll number");
        student.setRollNo(userInput.nextInt());

        System.out.println("Enter your Standard");
        student.setStd(userInput.nextInt());

        System.out.println("Enter your Age");
        student.setAge(userInput.nextInt());

        System.out.println("Enter your Father's Name");
        student.setFather(userInput.next());

        System.out.println("Enter your Mother's Name");
        student.setMother(userInput.next());

        System.out.println("Enter your City");
        student.setCity(userInput.next());

        System.out.println(student);

        boolean isaStudentAdd = studentDao.addStudent(student);

        System.out.println(isaStudentAdd ? "Student saved into database" : "Unable to save Student, please try again.");

        return student;
    }

    public void getStudentByRollNo(){

        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter your Roll number");
        Student student=studentDao.getStudentByRollNo(userInput.nextInt());

        System.out.println(student);
    }

    public void getAllStudent(){

        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter your Roll number");
        List<Student> studentList=studentDao.getAllStudent();

        studentList.forEach(student -> {
            System.out.println(student);
        });
    }
    public void deleteStudentByRollNo(){

        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter your Roll number");

        System.out.println(studentDao.deleteStudentById(userInput.nextInt())? "Student deleted successfully":
                "unable to delete student");

    }

    public static void main2(String[] args) {
        Scanner userInput = new Scanner(System.in);
        boolean isRunning = true;
        StudentService studentService = new StudentService();

        while (isRunning) {
            System.out.println("=======================================");

            System.out.println("0) nothing exit");
            System.out.println("1) Add User");
            System.out.println("2) Find Student by rollNo.");
            System.out.println("3) List of All Students");
            System.out.println("4) Delete Student by Roll no");

            int userAction = userInput.nextInt();

            switch (userAction) {
                case 0:
                    isRunning = false;
                    System.out.println("good bye.");
                    break;
                case 1:
                    studentService.addStudent();
                    break;
                case 2:
                    studentService.getStudentByRollNo();
                    break;
                case 3:
                    studentService.getAllStudent();
                    break;
                case 4:
                    studentService.deleteStudentByRollNo();
            }
        }
    }
}
