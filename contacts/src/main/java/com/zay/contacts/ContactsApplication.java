package com.zay.contacts;

import com.zay.contacts.dao.CountryRepo;
import com.zay.contacts.dao.HeroesRepo;
import com.zay.contacts.entities.Country;
import com.zay.contacts.entities.Hero;
import com.zay.contacts.initdata.CountryDataInit;
import com.zay.contacts.initdata.HeroDataIniti;
import com.zay.contacts.initdata.TrainingInitData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class ContactsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ContactsApplication.class, args);
    }


    @Autowired
    CountryRepo countryRepo;

    @Autowired
    HeroDataIniti heroDataIniti;


    @Autowired
    CountryDataInit countryDataInit;

    @Autowired
    TrainingInitData trainingInitData;

    @Override
    public void run(String... args) throws Exception {
    }



}
