package com.siddhant.class_management.view;

import com.siddhant.class_management.dao.StudentDao;
import com.siddhant.class_management.model.Student;
import com.siddhant.class_management.model.StudentTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;


public class Main {
    private JTextArea area;
    private JButton allStudentsButton,
            addStudentButton, updateStudentButton,
            findStudentByRollNoButton, removeStudentButton;
    private JTable table;
    private JDialog addStudentDialog, removeStudentDialog,studentByRollNoDialog;
    private JTextField nameInput,
            rollNoInput, stdInput, ageInput, fatherInput, motherInput, cityInput;
    private StudentDao studentDao;

    public Main(){
        this.studentDao=new StudentDao();
    }

    public static void main(String[] args) {
        new Main().paintWindow();

    }
    private void paintWindow(){

        //ctrl+f=find
        JFrame frame = new JFrame("CLASS MANAGEMENT");
        Dimension dimension = new Dimension(1100, 600);
        frame.setMinimumSize(dimension);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //paint main menu buttons
        mainMenuButtons(frame);

        //paint table
        paintAllStudentsTabel(frame);

        //dialog
        addStudentDialog = new JDialog(frame, "Add Student", Dialog.ModalityType.APPLICATION_MODAL);
        addStudentDialog.setLayout(null);
        addStudentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension dimensionOfDialog = new Dimension(500, 500);
        addStudentDialog.setMinimumSize(dimensionOfDialog);

        removeStudentDialog= new JDialog(frame, "Delete Student by Roll no.", Dialog.ModalityType.APPLICATION_MODAL);
        removeStudentDialog.setLayout(null);
        removeStudentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension dimensionOfremoveStudentDialog = new Dimension(600, 300);
        removeStudentDialog.setMinimumSize(dimensionOfremoveStudentDialog);

        studentByRollNoDialog= new JDialog(frame, "Student by Roll no.", Dialog.ModalityType.APPLICATION_MODAL);
        studentByRollNoDialog.setLayout(null);
        studentByRollNoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        Dimension studentByRollNoDialogDimension = new Dimension(600, 300);
        studentByRollNoDialog.setMinimumSize(studentByRollNoDialogDimension);

        allStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //paint table
                refreshTableData(table, studentDao.getAllStudent());
            }
        });

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addStudentForm();
            }
        });

        updateStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                area.setText("update student");
            }
        });

        findStudentByRollNoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                findStudentByRollNo();

            }
        });

        removeStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               removeStudent();
            }
        });

        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void findStudentByRollNo() {

        rollNoInput = new JTextField();
        rollNoInput.setBounds(200, 50, 200, 30);
        studentByRollNoDialog.add(rollNoInput);

        JButton findStudentBtn = new JButton("Search Student");
        findStudentBtn.setBounds(200, 100, 200, 30);
        studentByRollNoDialog.add(findStudentBtn);
        findStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Student studentByRollNo = studentDao.getStudentByRollNo(Integer.parseInt(rollNoInput.getText()));

                if (studentByRollNo!=null) {
                    refreshTableData(table, Arrays.asList(studentByRollNo));
                    studentByRollNoDialog.dispose();
                }else {
                    JOptionPane.showMessageDialog(null, "Student not found","Message", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        studentByRollNoDialog.setVisible(true);
    }

    private void removeStudent() {

        rollNoInput = new JTextField();
        rollNoInput.setBounds(200, 50, 200, 30);
        removeStudentDialog.add(rollNoInput);

        JButton deleteStudentBtn = new JButton("Delete Student");
        deleteStudentBtn.setBounds(200, 100, 200, 30);
        removeStudentDialog.add(deleteStudentBtn);

        deleteStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean isDeleted = studentDao.deleteStudentById(Integer.parseInt(rollNoInput.getText()));
                System.out.println(isDeleted?"Student is deleted successfully":"Try again, unable to delete student");
                removeStudentDialog.dispose();
            }
        });
        removeStudentDialog.setVisible(true);
    }

    private void addStudentForm() {
        nameInput = new JTextField("Name");
        nameInput.setBounds(150, 50, 200, 30);
        addStudentDialog.add(nameInput);

        rollNoInput = new JTextField("rollNo");
        rollNoInput.setBounds(150, 100, 200, 30);
        addStudentDialog.add(rollNoInput);

        stdInput = new JTextField("std");
        stdInput.setBounds(150, 150, 200, 30);
        addStudentDialog.add(stdInput);

        ageInput = new JTextField("age");
        ageInput.setBounds(150, 200, 200, 30);
        addStudentDialog.add(ageInput);

        fatherInput = new JTextField("father");
        fatherInput.setBounds(150, 250, 200, 30);
        addStudentDialog.add(fatherInput);

        motherInput = new JTextField("mother");
        motherInput.setBounds(150, 300, 200, 30);
        addStudentDialog.add(motherInput);

        cityInput = new JTextField("city");
        cityInput.setBounds(150, 350, 200, 30);
        addStudentDialog.add(cityInput);

        JButton saveStudentBtn = new JButton("Save Student");
        saveStudentBtn.setBounds(150, 400, 200, 30);
        saveStudentBtn.setBackground(new Color(0xFF3100));
        saveStudentBtn.setForeground(new Color(255,255,255));
        //saveStudentBtn.setCursor(new Cursor(5));
        addStudentDialog.add(saveStudentBtn);

        saveStudentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Student student = new Student();
                student.setName(nameInput.getText());
                student.setCity(cityInput.getText());
                student.setMother(motherInput.getText());
                student.setFather(fatherInput.getText());
                student.setStd(Integer.parseInt(stdInput.getText()));
                student.setAge(Integer.parseInt(ageInput.getText()));
                student.setRollNo(Integer.parseInt(rollNoInput.getText()));

                System.out.println(studentDao.addStudent(student)?
                        "Student is added":" Try again, failed to add student");
            }
        });
        addStudentDialog.setVisible(true);
    }

    private void paintAllStudentsTabel(JFrame frame){
        List<Student> studentList = studentDao.getAllStudent();
        StudentTableModel model = new StudentTableModel(studentList);

        table = new JTable(model);
        table.setBounds(550, 0, 550, 600);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        frame.add(scrollPane);
        JPanel jPanel = new JPanel();
        jPanel.add(scrollPane);
        jPanel.setBounds(550, 0, 550, 600);
        jPanel.setVisible(true);
        frame.add(jPanel);
    }

    private void refreshTableData(JTable table, List<Student> studentList){
                table.setModel(new StudentTableModel(studentList));
    }
    private void mainMenuButtons(JFrame frame){
        allStudentsButton = new JButton("All Students");
        allStudentsButton.setBounds(150, 100, 200, 30);
        frame.add(allStudentsButton);

        addStudentButton = new JButton("Add Student");
        addStudentButton.setBounds(150, 160, 200, 30);
        frame.add(addStudentButton);

        updateStudentButton = new JButton("Update Student Data");
        updateStudentButton.setBounds(150, 220, 200, 30);
        frame.add(updateStudentButton);

        findStudentByRollNoButton = new JButton("Get student by Roll no");
        findStudentByRollNoButton.setBounds(150, 280, 200, 30);
        frame.add(findStudentByRollNoButton);

        removeStudentButton = new JButton("Remove student");
        removeStudentButton.setBounds(150, 340, 200, 30);
        frame.add(removeStudentButton);
    }
}