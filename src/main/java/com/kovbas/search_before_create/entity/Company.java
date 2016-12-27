package com.kovbas.search_before_create.entity;

import javax.persistence.*;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    /**
     * Creates new Company object
     */
    public Company() {}

    /**
     * Creates new Company object with given name
     *
     * @param name
     */
    public Company(String name) {
        setName(name);
    }

    /**
     *  Creates new Company object with given name and description
     * @param name
     * @param description
     */
    public Company(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
