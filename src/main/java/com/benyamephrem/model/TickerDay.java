package com.benyamephrem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Ticker Data")
public class TickerDay {

    @Id
    private String id; //The id MongoDB will assign each index

    //Sort the documents in ascending order based on date
    @Indexed(name = "date_index", direction = IndexDirection.ASCENDING)
    private Date date; //date

    private Double open; //open price
    private Double high; //high price
    private Double low; //low price
    private Double close; //close price
    private Double volume;
    private Double dividend;
    private Double split;
    private Double openAdjusted;
    private Double highAdjusted;
    private Double lowAdjusted;
    private Double closeAdjusted;
    private Double volumeAdjusted;
    private Double firstDelta; //The change between this ticker day and the next ticker day
    private Double secondDelta; //The change between this ticker's first delta and the next ticker's delta

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

    public TickerDay(Date date, Double open, Double high, Double low, Double close, Double volume,
                     Double dividend, Double split, Double openAdjusted, Double highAdjusted,
                     Double lowAdjusted, Double closeAdjusted, Double volumeAdjusted, Double firstDelta,
                     Double secondDelta) {
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.dividend = dividend;
        this.split = split;
        this.openAdjusted = openAdjusted;
        this.highAdjusted = highAdjusted;
        this.lowAdjusted = lowAdjusted;
        this.closeAdjusted = closeAdjusted;
        this.volumeAdjusted = volumeAdjusted;
        this.firstDelta = firstDelta;
        this.secondDelta = secondDelta;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getDividend() {
        return dividend;
    }

    public void setDividend(Double dividend) {
        this.dividend = dividend;
    }

    public Double getSplit() {
        return split;
    }

    public void setSplit(Double split) {
        this.split = split;
    }

    public Double getOpenAdjusted() {
        return openAdjusted;
    }

    public void setOpenAdjusted(Double openAdjusted) {
        this.openAdjusted = openAdjusted;
    }

    public Double getHighAdjusted() {
        return highAdjusted;
    }

    public void setHighAdjusted(Double highAdjusted) {
        this.highAdjusted = highAdjusted;
    }

    public Double getLowAdjusted() {
        return lowAdjusted;
    }

    public void setLowAdjusted(Double lowAdjusted) {
        this.lowAdjusted = lowAdjusted;
    }

    public Double getCloseAdjusted() {
        return closeAdjusted;
    }

    public void setCloseAdjusted(Double closeAdjusted) {
        this.closeAdjusted = closeAdjusted;
    }

    public Double getVolumeAdjusted() {
        return volumeAdjusted;
    }

    public void setVolumeAdjusted(Double volumeAdjusted) {
        this.volumeAdjusted = volumeAdjusted;
    }

    public Double getFirstDelta() {
        return firstDelta;
    }

    public void setFirstDelta(Double firstDelta) {
        this.firstDelta = firstDelta;
    }

    public Double getSecondDelta() {
        return secondDelta;
    }

    public void setSecondDelta(Double secondDelta) {
        this.secondDelta = secondDelta;
    }
}
