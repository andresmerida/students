package com.test.students;

import com.test.students.core.entities.Class;
import com.test.students.core.entities.Student;
import com.test.students.core.repositories.ClassRepository;
import com.test.students.core.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentSolution implements CommandLineRunner {
    private final static Logger LOG = LoggerFactory.getLogger(StudentSolution.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    public static void main(String[] args) {
        SpringApplication.run(StudentSolution.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            LOG.info("Loading sample data");
            if (args.length == 0) { // we don't need arguments

                final Student[] students = {
                        studentRepository.save(new Student("Andres", "Merida")),
                        studentRepository.save(new Student("Ana", "Arevalo")),
                        studentRepository.save(new Student("Juan", "Perez")),
                        studentRepository.save(new Student("Armando", "Carpas"))
                };

                final Class[] classes = {
                        classRepository.save(new Class("English", "Engish class")),
                        classRepository.save(new Class("Intro 101", "Basic programation")),
                        classRepository.save(new Class("Intro 102", "Basic programation 2"))
                };

                LOG.info("Added {} Students, and {} Classes", students.length, classes.length);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            LOG.error(e.getMessage());
        }
    }
}
