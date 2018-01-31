package com.s4.students.core.services.impl;

import com.s4.students.core.entities.Class;
import com.s4.students.core.repositories.ClassRepository;
import com.s4.students.core.services.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    private final ClassRepository classRepository;

    @Autowired
    public ClassServiceImpl(final ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public Class getById(Serializable id) {
        return classRepository.findOne((Long) id);
    }

    @Override
    public Class save(Class classEntity) {
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
    public void delete(Serializable id) {
        classRepository.delete((Long) id);
    }

    @Override
    public List<Class> getAll() {
        return classRepository.findAll();
    }

}
