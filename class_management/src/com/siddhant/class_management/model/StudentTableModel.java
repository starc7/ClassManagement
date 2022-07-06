package com.siddhant.class_management.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class StudentTableModel extends AbstractTableModel {
    List<Student> students=new ArrayList<>();

    public StudentTableModel(List<Student> students) {
        this.students = new ArrayList<>(students);
    }

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int column) {
        String name = "??";

        switch (column) {
            case 0:
                name = "name";
                break;
            case 1:
                name = "rollNo";
                break;
            case 2:
                name = "std";
                break;
            case 3:
                name = "age";
                break;
            case 4:
                name = "father";
                break;
            case 5:
                name = "mother";
                break;
            case 6:
                name = "city";
                break;

        }
        return name;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class type = String.class;
        switch (columnIndex) {
            case 0:
            case 1:
                type = Integer.class;
                break;
        }
        return type;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        Object value = null;

        switch (columnIndex) {
            case 0:
                value = student.getName();
                break;
            case 1:
                value = student.getRollNo();
                break;
            case 2:
                value = student.getStd();
                break;
            case 3:
                value=student.getAge();
                break;
            case 4:
                value=student.getFather();
                break;
            case 5:
                value=student.getMother();
                break;
            case 6:
                value=student.getCity();
                break;
        }
        return value;
    }
}
