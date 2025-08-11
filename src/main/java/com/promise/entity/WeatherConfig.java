package com.promise.entity;

import lombok.Data;

@Data
public class WeatherConfig {

    private int id;
    private int boardId;
    private int localCode;
    private int sendingFrequency;
    private String apiKey;

}
