package com.benyamephrem.service.dto.stock;

import com.benyamephrem.service.dto.Dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TimeSeries extends Dto{

    @JsonProperty("2017-11-10")
    private Day Day;

    public Day getDay() {
        return Day;
    }
}
