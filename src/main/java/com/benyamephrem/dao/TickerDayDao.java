package com.benyamephrem.dao;

import com.benyamephrem.model.TickerDay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface TickerDayDao extends MongoRepository<TickerDay, String>{

    List<TickerDay> findAll();

    @Query("{ 'date' : { $gt: ?0 } }")
    List<TickerDay> findByDate(Date date); //Stored as ISO date aka "yyyy-mm-dd" ----> ISO date before passed into here

    //$gte means >= and $lte means <=
    @Query("{ 'date' : {$gte: ?0, $lt: ?1} }")
    List<TickerDay> findByDateRange(Date start, Date end); //Again remember that what you pass in here must be parsed to ISO
}