package com.s4.students.controllers;

import com.s4.students.core.entities.Class;
import com.s4.students.core.entities.Student;
import com.s4.students.core.services.StudentService;
import com.s4.students.core.utils.SearchCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Student>> listAllStudents(@RequestParam(value = "search",
            required = false) String search) {
        if (search != null) {
            List<SearchCriteria> params = new ArrayList<SearchCriteria>();
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }

            LOG.debug("search students...");
            return new ResponseEntity<List<Student>>(studentService.searchStudents(params), HttpStatus.OK);
        } else {
            LOG.debug("get all students");
            return new ResponseEntity<List<Student>>(studentService.getAll(), HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student studentPersisted = studentService.save(student);
        LOG.debug("Added:: " + student);

        return new ResponseEntity<Student>(studentPersisted, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") final Long id) {
        Student student = studentService.getById(id);
        if (student == null) {
            LOG.debug("Student with id " + id + " does not exists");
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }

        LOG.debug("Found student:: " + student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> editStudent(@RequestBody Student student,
                                            @PathVariable("studentId") final Long id) {
        Student studentUpdated = studentService.getById(id);
        if (studentUpdated != null) {
            studentService.updateStudent(id, student);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            LOG.debug("Student with id " + id + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{studentId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeStudent(@PathVariable("studentId") final Long id) {
        final Student studentDelete = studentService.getById(id);

        if (studentDelete != null) {
            studentService.delete(id);
            LOG.debug("Student with id " + id + " deleted");

            // Note this return 204/No Content regardless the class has actually been deleted or not,
            // to be idempotent
            return ResponseEntity.noContent().build();
        } else {
            LOG.debug("Student with id " + id + " does not exists");
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{studentId}/classes", method = RequestMethod.GET)
    public ResponseEntity<List<Class>> listStudentsByClass(@PathVariable("studentId") final Long id) {
        Student studentEntity = studentService.getById(id);

        if (studentEntity == null) {
            LOG.debug("Student with id " + id + " does not exists");
            return new ResponseEntity<List<Class>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Class>>(studentEntity.getClasses(), HttpStatus.OK);
    }
}
