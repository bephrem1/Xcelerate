package com.benyamephrem.service.resttemplate.stock;

import com.benyamephrem.service.dto.stock.Stock;

//Here we define what methods our implementation must implement
public interface StockService {
    Stock getStockHistoryByTicker(String ticker);
}
