package com.zay.contacts.web;


import com.zay.contacts.dao.SessionRepo;
import com.zay.contacts.entities.Hero;
import com.zay.contacts.entities.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@CrossOrigin("*")
@RestController()
@Transactional
public class SessionController {

    @Autowired
    SessionRepo sessionRepo;

    @GetMapping("sessions/hero/{id}")
    public Collection<Session> getSessionsByHero( @PathVariable("id") Long id){
        return sessionRepo.findSessionsByHero(id);
    }

    @GetMapping("sessions/{id}")
    public Session getSessionsById( @PathVariable("id") Long id){
        return sessionRepo.findById(id).get();
    }

    @PostMapping("sessions/{id}")
    public void addHeresToSession(@PathVariable("id") Long id , @RequestBody  Collection<Hero> heroes){
        Session session = sessionRepo.findById(id).get();
        session.getHeroes().addAll(heroes);
        sessionRepo.save(session);
    }

    @DeleteMapping("sessions/{id}")
    public void deletHeroFromSession(@PathVariable("id") Long sessionId, @RequestParam("hero") Long heroId){
        Session session = sessionRepo.findById(sessionId).get();
        Hero hero = session.getHeroes().stream().filter(h -> h.getId().equals(heroId)).findAny().orElse(null);
        session.getHeroes().remove(hero);
        sessionRepo.save(session);

    }


}


