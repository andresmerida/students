package com.test.students.core.utils;

import com.test.students.core.entities.Student;

public class SingleInstanceStudent {
    //create an object of SingleCreateStudent
    private static SingleInstanceStudent instance = new SingleInstanceStudent();

    //make the constructor private so that this class cannot be instantiated
    private SingleInstanceStudent() {
    }

    //Get the only object available
    public static SingleInstanceStudent getInstance() {
        return instance;
    }

    public Student getInstanceStudent(String firstName, String lastName) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);

        return student;
    }
}
