package com.zay.contacts.initdata;

import com.zay.contacts.dao.HeroesRepo;
import com.zay.contacts.entities.Country;
import com.zay.contacts.entities.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


@Component
public class HeroDataIniti {

    @Autowired
    HeroesRepo heroesRepo;

    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public static List<Hero> getHeroes() throws IOException {
        String[] names = {"Mohamed","Ali","Salah","Mourad","Mounira"};
        String[] lastNames = {"Jelassi","Ayari","Abassi","Abderrahim","Oueslati"};
        String[] aderesses =  {"Tunis","Sfax","Jendouba","Jerba","Gafssa"};
        String[] professions=  {"Eng","Student","Agricultor","Teatcher","Police"};
        String[] sex = {"M","F"};

        List<Hero> heroes = new ArrayList<>();
        File file = new File("/home/mootaz/projects/img/unknown.png");
        BufferedImage image = null;
        image = ImageIO.read(file);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( image, "png", baos );
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        for (int i = 0 ; i < names.length; i++) {
            try {
                Date bd = new Date(ThreadLocalRandom.current()
                        .nextLong(df.parse("01/01/1970").getTime(), df.parse("31/12/1999").getTime()));

                Random rand = new Random();
                Country c = CountryDataInit.getCountries().get(rand.nextInt(CountryDataInit.getCountries().size()));

                Hero hero = new Hero();

                hero.setName(names[i]);
                hero.setLastName(lastNames[i]);
                hero.setBirthDate(bd);
                hero.setEmail(hero.getName()+hero.getLastName()+"@zay.com");
                hero.setAddress(aderesses[i]);
                hero.setProfession(professions[i]);
                hero.setPhoneNumber(Long.valueOf(rand.nextInt(1000000)));
                hero.setSex(sex[rand.nextInt(2)]);


                hero.setProfilePic(imageInByte);

                hero.setCountry(c);

                heroes.add(hero);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return heroes;
    }

    @Bean
    public void initHeroes(){
        try {
            getHeroes().forEach(h ->{
                heroesRepo.save(h);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
