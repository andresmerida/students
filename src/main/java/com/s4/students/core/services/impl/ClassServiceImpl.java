package com.s4.students.core.services.impl;

import com.s4.students.core.entities.Class;
import com.s4.students.core.repositories.ClassRepository;
import com.s4.students.core.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;

    @Autowired
    public ClassServiceImpl(final ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public Class findOne(Long id) {
        return classRepository.findOne(id);
    }

    @Override
    public Class createClass(Class classEntity) {
        return classRepository.save(classEntity);
    }

    @Override
    public Class updateClass(Long id, Class classEntity) {
        Class classModified = classRepository.findOne(id);

        classModified.setTitle(classEntity.getTitle());
        classModified.setDescription(classEntity.getDescription());

        return classRepository.save(classModified);
    }

    @Override
    public void removeClass(Class classEntity) {
        classRepository.delete(classEntity);
    }

    @Override
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public List<Class> findBy(String field, String value) {
        return new ArrayList<Class>();
    }

    @Override
    public List<Class> findByTitleAndDescription(String title, String description) {
        return new ArrayList<>();
    }
}
