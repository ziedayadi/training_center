package com.zay.contacts.dao;

import com.zay.contacts.entities.Hero;
import com.zay.contacts.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeroesRepo extends JpaRepository<Hero, Long> {

    List<Hero> findByNameContains(String name);

    @Query("select s.heroes from Session s where s.id = :id")
    List<Hero> findBySession(@Param("id") Long id);

    @Query("select h from Hero h where h.isTrainer = true")
    List<Hero> findTrainers();

}
