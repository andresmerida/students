package com.test.students.core.entities;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
public class Class extends AbstractPersistable<Long> {
    private static final long serialVersionUID = 8365902915574146481L;

    private String title;

    private String description;

    public Class() {
    }

    public Class(String title, String description) {
        this.title = title;
        this.description = description;
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
}
