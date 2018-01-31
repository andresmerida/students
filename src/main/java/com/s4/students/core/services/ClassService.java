package com.s4.students.core.services;

import com.s4.students.core.entities.Class;

import java.util.List;

public interface ClassService {

    Class findOne(Long id);

    Class createClass(Class classEntity);

    Class updateClass(Long id, Class classEntity);

    void removeClass(Class classEntity);

    List<Class> getAllClasses();

    List<Class> findBy(String field, String value);

    List<Class> findByTitleAndDescription(String title, String description);
}
