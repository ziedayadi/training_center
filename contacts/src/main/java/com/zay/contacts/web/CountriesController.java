package com.zay.contacts.web;


import com.zay.contacts.dao.CountryRepo;
import com.zay.contacts.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
public class CountriesController {

    @Autowired
    CountryRepo countryRepo ;


    @GetMapping("countries/all")
    List<Country> getAll(){
        return countryRepo.findAll();
    }
}
