package com.benyamephrem.service.dto.stock;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StockData {

    @JsonProperty("data")
    private List<List<Object>> stockFinancialData;
    private String name;
    private String description;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    public List<List<Object>> getStockFinancialData() {
        return stockFinancialData;
    }

    public void setStockFinancialData(List<List<Object>> stockFinancialData) {
        this.stockFinancialData = stockFinancialData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

//The outer list is a list of each date starting from today and going back
//The inner list consists of the data for that day composed of indices 0-12
//Index 0: Date (String)
//Index 1: Open (Double)
//Index 2: High (Double)
//Index 3: Low (Double)
//Index 4: Close (Double)
//Index 5: Volume (Double)
//Index 6: Dividend (Double)
//Index 7: Split (Double)
//Index 8: Adj_Open (Double)
//Index 9: Adj_High (Double)
//Index 10: Adj_Low (Double)
//Index 11: Adj_Close (Double)
//Index 12: Adj_Volume (Double)
