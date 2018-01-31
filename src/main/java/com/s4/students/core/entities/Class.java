package com.s4.students.core.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.List;

@Entity
public class Class extends AbstractPersistable<Long> {
    private static final long serialVersionUID = 8365902915574146481L;

    private String title;

    private String description;

    @ManyToMany(fetch=FetchType.LAZY)
    @JsonBackReference
    @JoinTable(name="CLASS_STUDENT",
            joinColumns=
            @JoinColumn(name="CLASS_ID", referencedColumnName="ID"),
            inverseJoinColumns=
            @JoinColumn(name="STUDENT_ID", referencedColumnName="ID")
    )
    public List<Student> students;

    public Class() {
    }

    public Class(String title, String description, List<Student> students) {
        this.title = title;
        this.description = description;
        this.students = students;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getStudents() {
        return students;
    }
}
