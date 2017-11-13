package com.benyamephrem.service.resttemplate.stock;

//StockService implementation. Fetches quote data and passes them through dto's

import com.benyamephrem.service.dto.stock.Stock;
import com.benyamephrem.service.resttemplate.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("api.properties")
public class StockServiceImpl extends RestApiService<Stock> implements StockService {

    private String name;
    private String key;
    private String host;

    @Autowired
    public StockServiceImpl(
            @Value("${quandl.api.name}") String name, //Inject values in from application.properties file
            @Value("${quandl.api.key}") String key,
            @Value("${quandl.api.host}") String host) {
        super();
        this.name = name;
        this.key = key;
        this.host = host;
    }

    //This is where the request URL is built out...remember that the host is set in api.properties
    //so switch that if you are using the sandbox.
    //Ex: https://etws.etrade.com/market/rest/quote/GOOG?detailFlag=ALL
    @Override
    public Stock getStockHistoryByTicker(String ticker) {
        return get("/api/v3/datasets/EOD/{ticker}.json?api_key={api_key}")
                .param("ticker", ticker)
                .param("api_key", getApiKey())
                .execute();
    }

    public String getName() {
        return name;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getApiKey() {
        return key;
    }

    @Override
    public Class<Stock> getDtoClass() {
        return Stock.class;
    }

}
