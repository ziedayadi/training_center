package com.zay.contacts.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn
    private Training training;

    @Temporal(TemporalType.DATE)
    private Date date;

    @JsonIgnore
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    @JoinTable(name = "SESSION_HERO",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "hero_id")
    )
    private Collection<Hero> heroes = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Hero trainer;

    public Session(Training training, Date date) {
        this.training = training;
        this.date = date;
        this.heroes = heroes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Collection<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Collection<Hero> heroes) {
        this.heroes = heroes;
    }


    public Hero getTrainer() {
        return trainer;
    }

    public void setTrainer(Hero trainer) {
        this.trainer = trainer;
    }


    public Session() {
    }
}
