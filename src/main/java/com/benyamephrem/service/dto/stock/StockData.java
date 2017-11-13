package com.benyamephrem.service.dto.stock;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StockData {

    @JsonProperty("data")
    private List<List<Object>> stockFinancialData;

    public List<List<Object>> getStockFinancialData() {
        return stockFinancialData;
    }

    public void setStockFinancialData(List<List<Object>> stockFinancialData) {
        this.stockFinancialData = stockFinancialData;
    }
}
