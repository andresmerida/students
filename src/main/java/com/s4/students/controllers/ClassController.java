package com.s4.students.controllers;

import com.s4.students.core.entities.Class;
import com.s4.students.core.entities.Student;
import com.s4.students.core.services.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClassController {

    private final static Logger LOG = LoggerFactory.getLogger(ClassController.class);

    private final ClassService classService;

    @Autowired
    public ClassController(final ClassService classService) {
        this.classService = classService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Class> listAllClasses() {
        return classService.getAllClasses();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Class> createClass(@RequestBody Class classEntity) {
        Class classPersisted = classService.createClass(classEntity);

        final HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<Class>(classPersisted, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> editStudent(@RequestBody Class classEntity,
                                            @PathVariable("classId") final String id) {
        Class studentUpdated = classService.updateClass(Long.parseLong(id), classEntity);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteClass(@PathVariable("classId") final String id) {

        final Class classDelete = classService.findOne(Long.parseLong(id));
        if (classDelete != null) {
            LOG.debug("Removed from classEntity collection id:{}", id);
            classService.removeClass(classDelete);
        }

        // Note this return 204/No Content regardless the class has actually been deleted or not, to be idempotent
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{classId}/students", method = RequestMethod.GET)
    public List<Student> listStudentsByClass(@PathVariable("classId") final String id) {
        Class classEntity = classService.findOne(Long.parseLong(id));

        return classEntity.getStudents();
    }
}
