package com.test.students.core.services;

import com.test.students.controllers.rbc.RbcClass;
import com.test.students.core.entities.Class;

import java.util.List;

public interface ClassService {

    Class findOne(Long id);

    Class createClass(Class classEntity);

    Class updateClass(Long id, RbcClass rbcClass);

    void removeClass(Class classEntity);

    List<Class> getAllClasses();

    List<Class> findBy(String field, String value);

    List<Class> findByTitleAndDescription(String title, String description);
}
