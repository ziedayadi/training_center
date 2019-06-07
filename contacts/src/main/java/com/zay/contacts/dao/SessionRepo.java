package com.zay.contacts.dao;

import com.zay.contacts.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface  SessionRepo extends JpaRepository<Session,Long> {

    Collection<Session> findByTrainingId(Long id);
    @Query("select h.sessions from Hero h where h.id = :id")
    public List<Session> findSessionsByHero(@Param("id") Long id);

}
