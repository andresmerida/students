package com.test.students.core.repositories;

import com.test.students.core.entities.Student;
import com.test.students.core.utils.SearchCriteria;

import java.util.List;

public interface SearchStudentCustomRepository {

    List<Student> searchStudent(List<SearchCriteria> params);
}
