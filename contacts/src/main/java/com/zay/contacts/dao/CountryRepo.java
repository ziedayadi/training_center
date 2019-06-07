package com.zay.contacts.dao;

import com.zay.contacts.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country,String> {
}
