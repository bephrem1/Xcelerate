package com.benyamephrem.tasking;

import com.benyamephrem.dao.TickerDayDao;
import com.benyamephrem.model.TickerDay;
import com.benyamephrem.service.dto.stock.Stock;
import com.benyamephrem.service.dto.stock.StockData;
import com.benyamephrem.service.resttemplate.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component //So that spring picks up this class as a component
public class DatabaseSeeder implements CommandLineRunner {

    //Our DAO object that persists the configured documents
    @Autowired
    private TickerDayDao tickerDayDao;

    //Our stock service to fetch the api data so we can process it here
    @Autowired
    private StockService stockService;

    @Override
    public void run(String... args) throws Exception {
        //Run task on applications startup, in this case will be used for test DB seeding

        /*

        Basically this is what is happening...
        Value - Stock value
        Δ: First Derivative - Velocity (change between two points)
        ΔΔ: Second Derivative - Acceleration (change between the change between two points)
        +-------+-------+--------+--------+----------------+--------------------+
        | Value | $3.00 | $4.00  | $2.00  | $10.00 (prev)  | n                  |
        +-------+-------+--------+--------+----------------+--------------------+
        | Δ     | x     | +$1.00 | -$2.00 | +$8.00 (prevΔ) | n - prev           |
        +-------+-------+--------+--------+----------------+--------------------+
        | ΔΔ    | x     | x      | -$3.00 | +$10.00        | (n - prev) - prevΔ |
        +-------+-------+--------+--------+----------------+--------------------+

        */

        //Hardcoded ticker for now, just trying to get functionality down for now before flexibility is a concern
        Stock stock = stockService.getStockHistoryByTicker("AAPL");

        //Remember: Our outer list is the individual dates, the inner list
        List<List<Object>> stockHistory = stock.getData().getStockFinancialData();



        //Go through each day in the returned stock history and do some formatting before persisting it to the DB
        for(int i = 0; i < stock.getData().getStockFinancialData().size(); i++){
            List<Object> data = stockHistory.get(i);
            Double firstDelta = null;
            Double secondDelta = null;

            //Calculate the first derivative with null taken into account
            if(i > 0){
                //This day's close minus the previous day's close to get the change in value between days
                firstDelta = (Double) data.get(4) - (Double) stockHistory.get(i-1).get(4);
            }

            //Calculate the second derivative with null taken into account
            if(i > 1){
                Double prevFirstDelta = (Double) stockHistory.get(i-1).get(4) - (Double) stockHistory.get(i-2).get(4);
                secondDelta = firstDelta - prevFirstDelta;
            }

            TickerDay day = new TickerDay(
                //Date string converted to Iso Date to make database searching possible
                new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(data.get(0))),
                (Double) data.get(1), //Open
                (Double) data.get(2), //High
                (Double) data.get(3), //Low
                (Double) data.get(4), //Close
                (Double) data.get(5), //Volume
                (Double) data.get(6), //Dividend
                (Double) data.get(7), //Split
                (Double) data.get(8), //Adj_Open
                (Double) data.get(9), //Adj_High
                (Double) data.get(10), //Adj_Low
                (Double) data.get(11), //Adj_Close
                (Double) data.get(12), //Adj_Volume
                firstDelta,
                secondDelta
            );
            tickerDayDao.save(day);
        }
    }

}
