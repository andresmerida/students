package com.s4.students;

import com.s4.students.core.entities.Class;
import com.s4.students.core.entities.Student;
import com.s4.students.core.repositories.ClassRepository;
import com.s4.students.core.repositories.StudentRepository;
import com.s4.students.core.utils.SingleInstanceStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.util.Arrays.asList;

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
                        studentRepository.save(SingleInstanceStudent.getInstance()
                                .getInstanceStudent("Andres", "Merida")),
                        studentRepository.save(SingleInstanceStudent.getInstance()
                                .getInstanceStudent("Ana", "Arevalo")),
                        studentRepository.save(SingleInstanceStudent.getInstance()
                                .getInstanceStudent("Juan", "Perez")),
                        studentRepository.save(SingleInstanceStudent.getInstance()
                                .getInstanceStudent("Armando", "Carpas"))
                };

                final Class[] classes = {
                        classRepository.save(new Class("English", "English Course",
                                asList(students[0], students[1]))),
                        classRepository.save(new Class("Intro Progra", "Programation basic",
                                asList(students[2], students[3])))
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
