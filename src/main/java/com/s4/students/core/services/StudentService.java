package com.s4.students.core.services;

import com.s4.students.core.entities.Student;
import com.s4.students.core.utils.SearchCriteria;

import java.util.List;

public interface StudentService {

    Student findOne(Long id);

    Student createStudent(Student student);

    Student updateStudent(Long id, Student student);

    void removeStudent(Student student);

    List<Student> getAllStudents();

    List<Student> searchStudents(List<SearchCriteria> params);
}
