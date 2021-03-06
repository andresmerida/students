package com.s4.students.core.services.impl;

import com.s4.students.core.entities.Student;
import com.s4.students.core.repositories.SearchStudentCustomRepository;
import com.s4.students.core.repositories.StudentRepository;
import com.s4.students.core.services.StudentService;
import com.s4.students.core.utils.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final SearchStudentCustomRepository searchStudentCustomRepository;

    @Autowired
    public StudentServiceImpl(final StudentRepository studentRepository,
                              final SearchStudentCustomRepository searchStudentCustomRepository) {
        this.studentRepository = studentRepository;
        this.searchStudentCustomRepository = searchStudentCustomRepository;
    }

    @Override
    public Student getById(Serializable id) {
        return studentRepository.findOne((Long) id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student studentModified = studentRepository.findOne(id);

        studentModified.setFirstName(student.getFirstName());
        studentModified.setLastName(student.getLastName());

        return studentRepository.save(studentModified);
    }

    @Override
    public void delete(Serializable id) {
        studentRepository.delete((Long) id);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchStudents(List<SearchCriteria> params) {
        return searchStudentCustomRepository.searchStudent(params);
    }

}
