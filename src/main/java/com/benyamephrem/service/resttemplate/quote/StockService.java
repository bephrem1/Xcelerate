package com.benyamephrem.service.resttemplate.quote;

import com.benyamephrem.service.dto.quote.Stock;

//Here we define what methods our implementation must implement
public interface StockService {
    Stock getStockHistoryByTicker(String ticker);
}
