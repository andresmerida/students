package com.s4.students.core.repositories;

import com.s4.students.core.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
}
