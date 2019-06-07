package com.zay.contacts.dao;

import com.zay.contacts.entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainingRepo extends JpaRepository<Training, Long> {

    // List<Training> findByNameContainingOrDescriptionContaining(String kewWord);

    @Query("select t from Training t where " +
            "t.name like CONCAT('%',:kw,'%') " +
            "OR t.reference like CONCAT('%',:kw,'%') " +
            "OR t.description like CONCAT('%',:kw,'%') ")
    List<Training> findByKeyWord(@Param("kw") String keyWord);


}
