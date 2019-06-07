package com.zay.contacts.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
public class Country implements Serializable {

    @Id
    private String initials;
    private String name;

    public Country(String initials, String name) {
        this.initials = initials;
        this.name = name;
    }

    public Country() {
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
