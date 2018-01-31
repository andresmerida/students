package com.s4.students.controllers;

import com.s4.students.core.entities.Class;
import com.s4.students.core.entities.Student;
import com.s4.students.core.services.StudentService;
import com.s4.students.core.utils.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
    public List<Student> listAllStudents(@RequestParam(value = "search", required = false) String search) {
        if (search != null) {
            List<SearchCriteria> params = new ArrayList<SearchCriteria>();
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }

            LOG.debug("search students...");
            return studentService.searchStudents(params);
        } else {
            LOG.debug("get all students");
            return studentService.getAllStudents();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
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
    public ResponseEntity<Void> editStudent(@RequestBody Student student,
                                            @PathVariable("studentId") final String id) {
        Student studentUpdated = studentService.updateStudent(Long.parseLong(id), student);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeStudent(@PathVariable("studentId") final String id) {
        final Student studentDelete = studentService.findOne(Long.parseLong(id));

        if (studentDelete != null) {
            LOG.debug("Removed from student collection id:{}", id);
            studentService.removeStudent(studentDelete);
        }

        // Note this return 204/No Content regardless the class has actually been deleted or not,
        // to be idempotent
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{studentId}/classes", method = RequestMethod.GET)
    public List<Class> listStudentsByClass(@PathVariable("studentId") final String id) {
        Student studentEntity = studentService.findOne(Long.parseLong(id));

        return studentEntity.getClasses();
    }
}
