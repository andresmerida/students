package com.test.students.controllers;

import com.test.students.controllers.rbc.RbcClass;
import com.test.students.core.entities.Class;
import com.test.students.core.services.ClassService;
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
    public ResponseEntity<Class> createClass(@RequestBody RbcClass rbcClass) {
        Class classEntity = new Class(rbcClass.getTitle(), rbcClass.getDescription());
        Class classPersisted = classService.createClass(classEntity);

        final HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<Class>(classPersisted, headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Class editClass(@RequestBody Class classEntity) {
        return new Class("title edited", "description edited");
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
}
