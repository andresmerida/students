package com.test.students.core.services;

import com.test.students.core.entities.Student;

import java.util.List;

public interface StudentService {

    Student findOne(Long id);

    Student createStudent(Student student);

    Student updateStudent(Long id, Student student);

    void removeStudent(Student student);

    List<Student> getAllStudents();

    List<Student> findBy(String field, String value);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);
}
