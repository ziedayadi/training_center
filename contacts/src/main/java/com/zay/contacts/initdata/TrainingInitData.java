package com.zay.contacts.initdata;



import com.zay.contacts.dao.HeroesRepo;
import com.zay.contacts.dao.SessionRepo;
import com.zay.contacts.dao.TrainingRepo;
import com.zay.contacts.entities.Hero;
import com.zay.contacts.entities.Session;
import com.zay.contacts.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
@Transactional
public class TrainingInitData {


    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");


    @Autowired
    TrainingRepo trainingRepo;

    @Autowired
    SessionRepo sessionRepo;

    @Autowired
    HeroesRepo heroesRepo;


    @Bean
    public void initTrainings(){
        Training training1 = new Training("JAVA_1","JAVA","JAVA TRAINING FOR BEGINNERS","PROGRAMMING SKILLS; OO", 2);
        Training training2 = new Training("DB1","SQL","SQL TRAINING FOR BEGINNERS","PROGRAMMING SKILLS; OO; DB", 3);
        Training training3 = new Training("JAVA2","JAVA","JAVA TRAINING FOR EXPERTS","JAVA1;PROGRAMMING SKILLS; OO; DB", 7);

        trainingRepo.save(training1);
        trainingRepo.save(training2);
        trainingRepo.save(training3);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        try {
            Session session1 = new Session(training1,dateFormat.parse("01/06/2019"));
            Session session2 = new Session(training1,dateFormat.parse("01/09/2019"));
            Session session3 = new Session(training1,dateFormat.parse("01/12/2019"));
            Session session4 = new Session(training2,dateFormat.parse("01/07/2019"));
            Session session5 = new Session(training2,dateFormat.parse("01/08/2019"));
            Session session6 = new Session(training3,dateFormat.parse("15/07/2019"));

            sessionRepo.save(session1);
            sessionRepo.save(session2);
            sessionRepo.save(session3);
            sessionRepo.save(session4);
            sessionRepo.save(session5);
            sessionRepo.save(session6);



        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.bindHeroesToSession();
        this.binSessionsToHero();
        this.initTrainer();
        this.bindTrainer();
    }

    private void binSessionsToHero() {
        Session session4 = sessionRepo.findById(4L).get();
        Session session5 = sessionRepo.findById(5L).get();
        Hero hero = heroesRepo.findById(5L).get();
        session4.getHeroes().add(hero);
        session5.getHeroes().add(hero);
        sessionRepo.save(session4);
        sessionRepo.save(session5);

    }

    private void bindHeroesToSession() {
        Session session = sessionRepo.findById(1L).get();
        Hero hero1 = heroesRepo.findById(1L).get();
        Hero hero2 = heroesRepo.findById(2L).get();
        Hero hero3 = heroesRepo.findById(3L).get();
        session.getHeroes().add(hero1);
        session.getHeroes().add(hero2);
        session.getHeroes().add(hero3);
        sessionRepo.save(session);
    }


    public void initTrainer()  {
        Hero hero1 = new Hero();
        hero1.setName("Jhony");
        hero1.setLastName("SINS");
        try {
            hero1.setBirthDate(df.parse("01/01/1970"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hero1.setEmail(hero1.getName()+hero1.getLastName()+"@zay.com");
        hero1.setAddress("Adress de Jhony Sins");
        hero1.setProfession("JAVA TRAINER");
        hero1.setPhoneNumber((long) 119988773);
        hero1.setSex("M");
        hero1.setTrainer(true);

        Hero hero2 = new Hero();
        hero2.setName("Mia");
        hero2.setLastName("KHALIFA");
        try {
            hero2.setBirthDate(df.parse("02/06/1988"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        hero2.setEmail(hero2.getName()+hero2.getLastName()+"@zay.com");
        hero2.setAddress("Adress de Mia");
        hero2.setProfession("C++ TRAINER");
        hero2.setPhoneNumber((long)169696969);
        hero2.setSex("M");
        hero2.setTrainer(true);

        this.heroesRepo.save(hero2);
        this.heroesRepo.save(hero1);

    }

    private void bindTrainer() {

        Hero t1 = heroesRepo.findTrainers().get(0);
        sessionRepo.findAll().stream().forEach(s -> {
            s.setTrainer(t1);
            sessionRepo.save(s);
        });
    }



}
