package com.s4.students.core.services;

import com.s4.students.core.entities.Student;
import com.s4.students.core.utils.SearchCriteria;

import java.util.List;

public interface StudentService extends CRUDService<Student> {

    Student updateStudent(Long id, Student student);

    List<Student> searchStudents(List<SearchCriteria> params);
}
