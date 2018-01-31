package com.s4.students.core.services;

import com.s4.students.core.entities.Class;

public interface ClassService extends CRUDService<Class> {

    Class updateClass(Long id, Class classEntity);

}
