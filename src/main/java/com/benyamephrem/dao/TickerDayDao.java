package com.benyamephrem.dao;

import com.benyamephrem.model.TickerDay;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface TickerDayDao extends MongoRepository<TickerDay, String>{

    List<TickerDay> findAll();

    //Projection fields specify the only fields we want returned, here we don't want id either since
    //we have no relationship to traverse between documents
    @Query("{ 'date' : ?0 }, { date: 1, open: 1, close: 1, _id: 0 }")
    List<TickerDay> findByDate(Date date); //Stored as ISO date aka "yyyy-mm-dd" ----> ISO date before passed into here

    //$gte means >= and $lte means <=
    @Query("{ 'date' : {$gte: ?0, $lte: ?1} }, { date: 1, open: 1, close: 1, _id: 0 }")
    List<TickerDay> findByDateRange(Date start, Date end); //Again remember that what you pass in here must be parsed to ISO
}