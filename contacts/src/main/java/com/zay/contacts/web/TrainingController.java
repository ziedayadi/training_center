package com.zay.contacts.web;


import com.zay.contacts.dao.SessionRepo;
import com.zay.contacts.dao.TrainingRepo;
import com.zay.contacts.entities.Session;
import com.zay.contacts.entities.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@CrossOrigin("*")
@RestController
@Transactional
public class TrainingController {

    @Autowired
    TrainingRepo trainingRepo;

    @Autowired
    SessionRepo sessionRepo;

    @GetMapping("trainings/all")
    public Collection<Training> findAll() {
        return trainingRepo.findAll();
    }

    @GetMapping("trainings/find")
    public Collection<Training> find(@RequestParam("kw") String keyWord) {
        return trainingRepo.findByKeyWord(keyWord);
    }

    @GetMapping("trainings/{id}")
    public Training findById(@PathVariable("id") Long id) {
        return this.trainingRepo.findById(id).get();
    }


    @PostMapping("trainings/save")
    public Training save(@RequestBody Training t) {
        return this.trainingRepo.save(t);
    }

    @DeleteMapping("trainings/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        this.trainingRepo.deleteById(id);
    }

    @GetMapping("trainings/{id}/sessions")
    public Collection<Session> getSessionsForTraining(@PathVariable("id") Long id) {
        return this.sessionRepo.findByTrainingId(id);
    }

    @PostMapping("trainings/session/save")
    public Collection<Session> saveSessions(@RequestBody Collection<Session> sessions) {
        return this.sessionRepo.saveAll(sessions);
    }


}
