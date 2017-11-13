package com.benyamephrem.service.dto.stock;

import com.benyamephrem.service.dto.Dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Stock extends Dto{

    //If we name a field a name other than what the JSON response calls it we have to explicitly use
    //@JsonProperty annotation. Otherwise Spring takes the variable name to be the JSON property name.
    //@JsonProperty("") <- DEFAULT
    @JsonProperty("Time Series (Daily)")
    private TimeSeries timeSeries; //Current asking price for a security

    public TimeSeries getTimeSeries() {
        return timeSeries;
    }
}
