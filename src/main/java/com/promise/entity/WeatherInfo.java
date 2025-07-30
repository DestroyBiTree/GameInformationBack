package com.promise.entity;

import lombok.Data;

@Data
public class WeatherInfo {

    private String province;         // 省份（如：陕西）
    private String city;             // 城市/区域（如：未央区）
    private String adcode;           // 区域编码（如：610112）
    private String weather;          // 天气状况（如：晴）
    private String temperature;      // 温度（字符串，如：35）
    private String temperatureFloat; // 温度（浮点型字符串，如：35.0）
    private String windDirection;    // 风向（如：东北）
    private String windPower;        // 风力（如：≤3）
    private String humidity;         // 湿度（字符串，如：38）
    private String humidityFloat;    // 湿度（浮点型字符串，如：38.0）
    private String reportTime;
}
