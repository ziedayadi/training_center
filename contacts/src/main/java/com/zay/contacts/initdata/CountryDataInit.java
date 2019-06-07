package com.zay.contacts.initdata;

import com.zay.contacts.dao.CountryRepo;
import com.zay.contacts.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Component
public class CountryDataInit {

    @Autowired
    CountryRepo countryRepo;

    public static List<Country> getCountries(){
        Country[] countries = {
              new Country("TU","Tunisia"),
              new Country("FR","France"),
              new Country("DE","Germany"),
              new Country("IT","Italy"),
              new Country("US","United States")
        };
        return Arrays.asList(countries);
    }


    @Bean
    private void initCountries() {

        CountryDataInit.getCountries().forEach(c -> countryRepo.save(c));
    }

}
