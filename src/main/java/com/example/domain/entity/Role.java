package com.example.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Chatzakis Nikolaos
 */
@Entity
public class Role extends BaseEntity {

    @Column(nullable = false)
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
