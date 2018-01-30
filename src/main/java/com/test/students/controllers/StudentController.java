package com.test.students.controllers;

import com.test.students.controllers.rbc.RbcStudent;
import com.test.students.core.entities.Student;
import com.test.students.core.services.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final static Logger LOG = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    @Autowired
    public StudentController(final StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> listAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> createStudent(@RequestBody RbcStudent rbcStudent) {
        Student student = new Student(rbcStudent.getFirstName(), rbcStudent.getLastName());
        Student studentPersisted = studentService.createStudent(student);

        final HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<Student>(studentPersisted, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") final String id) {

        final HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<Student>(studentService.findOne(Long.parseLong(id)),
                headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> editStudent(@RequestBody RbcStudent rbcStudent,
                                            @PathVariable("studentId") final String id) {
        Student studentUpdated = studentService.updateStudent(Long.parseLong(id), rbcStudent);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeStudent(@PathVariable("studentId") final String id) {
        final Student studentDelete = studentService.findOne(Long.parseLong(id));

        if (studentDelete != null) {
            LOG.debug("Removed from student collection id:{}", id);
            studentService.removeStudent(studentDelete);
        }

        // Note this return 204/No Content regardless the class has actually been deleted or not, to be idempotent
        return ResponseEntity.noContent().build();
    }
}
