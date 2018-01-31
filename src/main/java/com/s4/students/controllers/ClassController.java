package com.s4.students.controllers;

import com.s4.students.core.entities.Class;
import com.s4.students.core.entities.Student;
import com.s4.students.core.services.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Class>> listAllClasses() {
        LOG.debug("get all classes");
        return new ResponseEntity<>(classService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Class> createClass(@RequestBody Class classEntity) {
        Class classPersisted = classService.save(classEntity);
        LOG.debug("Added:: " + classPersisted);

        return new ResponseEntity<>(classPersisted, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.GET)
    public ResponseEntity<Class> getClass(@PathVariable("classId") final Long id) {
        Class classEntity = classService.getById(id);
        if (classEntity == null) {
            LOG.debug("ClassEntity with id " + id + " does not exists");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOG.debug("Found student:: " + classEntity);
        return new ResponseEntity<>(classEntity, HttpStatus.OK);
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.PUT)
    public ResponseEntity<Class> editStudent(@RequestBody Class classEntity,
                                            @PathVariable("classId") final Long id) {
        Class classPersisted = classService.getById(id);

        if (classPersisted != null) {
            Class classUpdated = classService.updateClass(id, classEntity);
            return new ResponseEntity<>(classUpdated, HttpStatus.OK);
        } else {
            LOG.debug("Class with id " + id + " does not exists");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{classId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteClass(@PathVariable("classId") final Long id) {
        final Class classDelete = classService.getById(id);

        if (classDelete != null) {
            classService.delete(id);
            LOG.debug("Class with id " + id + " deleted");

            return ResponseEntity.noContent().build();
        } else {
            LOG.debug("Class with id " + id + " does not exists");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{classId}/students", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listStudentsByClass(@PathVariable("classId") final Long id) {
        Class classEntity = classService.getById(id);

        if (classEntity == null) {
            LOG.debug("Class with id " + id + " does not exists");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(classEntity.getStudents(), HttpStatus.OK);
    }
}
