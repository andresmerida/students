package com.s4.students.core.repositories;

import com.s4.students.core.entities.Student;
import com.s4.students.core.utils.SearchCriteria;

import java.util.List;

public interface SearchStudentCustomRepository {

    List<Student> searchStudent(List<SearchCriteria> params);
}
