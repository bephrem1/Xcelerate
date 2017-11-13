package com.benyamephrem.service.dto.stock;

import com.benyamephrem.service.dto.Dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Stock extends Dto{

    //If we name a field a name other than what the JSON response calls it we have to explicitly use
    //@JsonProperty annotation. Otherwise Spring takes the variable name to be the JSON property name.
    //@JsonProperty("") <- DEFAULT
    @JsonProperty("dataset")
    private StockData data;

    public StockData getData() {
        return data;
    }

    public void setData(StockData data) {
        this.data = data;
    }
}
