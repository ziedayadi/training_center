package com.zay.contacts.web;


import com.zay.contacts.dao.HeroesRepo;
import com.zay.contacts.entities.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@CrossOrigin("*")
@RestController()
@Transactional
public class HeroesController {

    @Autowired
    HeroesRepo heroesRepo;


    @RequestMapping(method = RequestMethod.GET)
    public List<Hero> getAll() {
        return heroesRepo.findAll();
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "heroes/{id}")
    public void save(@PathVariable("id") Long id, @RequestBody Hero hero) {
        hero.setId(id);
        heroesRepo.save(hero);
    }

    @RequestMapping(value = "heroes/{id}")
    public Hero findOne(@PathVariable("id") Long id) {
        return heroesRepo.findById(id).get();
    }

    @RequestMapping(method = RequestMethod.POST, value = "heroes")
    public Hero save(@RequestBody Hero hero) {
        return heroesRepo.save(hero);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "heroes/{id}")
    public void deleteHero(@PathVariable("id") Long id) {
        heroesRepo.deleteById(id);
    }


    @RequestMapping(method = RequestMethod.GET, value = "heroes/name")
    public List<Hero> find(@RequestParam(name = "kw") String kw) {
        return heroesRepo.findByNameContains(kw);
    }

    @GetMapping(
            value = "/heroes/photo/{id}",
            produces = MediaType.IMAGE_PNG_VALUE
    )
    public @ResponseBody byte[] getHeroPhoto(@PathVariable("id") Long id) throws IOException {
        Hero hero = heroesRepo.findById(id).get();
        return hero.getProfilePic();
    }

    @GetMapping("heroes/session/{id}")
    public Collection<Hero> getHeroesBySession(@PathVariable("id") Long id){
        return this.heroesRepo.findBySession(id);
    }

    @RequestMapping(value = "/heroes/uploadPhoto/{id}",method = RequestMethod.POST)
    public void uploadPhoto(@RequestParam("file") MultipartFile file, @PathVariable("id")  Long id) throws IOException {
        Hero hero = heroesRepo.findById(id).get();
        hero.setProfilePic(file.getBytes());
        heroesRepo.save(hero);
    }

    @GetMapping("/heroes/trainers")
    public Collection<Hero> getTrainers(){
        return  this.heroesRepo.findTrainers();
    }

}
