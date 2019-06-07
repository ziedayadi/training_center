package com.zay.contacts.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
public class Training implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    private String reference;
    private String name;
    private String description;
    private String prerequisites;
    private int period;


    @OneToMany(mappedBy = "training", cascade = CascadeType.ALL)
    private Collection<Session> sessions;

    public Training(String reference, String name, String description, String prerequisites, int period) {
        this.reference = reference;
        this.name = name;
        this.description = description;
        this.prerequisites = prerequisites;
        this.period = period;
    }

    public Training() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
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

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

}
