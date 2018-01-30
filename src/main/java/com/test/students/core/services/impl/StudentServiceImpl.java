package com.test.students.core.services.impl;

import com.test.students.core.entities.Student;
import com.test.students.core.repositories.StudentRepository;
import com.test.students.core.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findOne(Long id) {
        return studentRepository.findOne(id);
    }

    @Override
    public Student createStudent(Student student) {
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
    public void removeStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findBy(String field, String value) {
        return new ArrayList<>();
    }

    @Override
    public List<Student> findByFirstNameAndLastName(String firstName, String lastName) {
        return new ArrayList<>();
    }
}
