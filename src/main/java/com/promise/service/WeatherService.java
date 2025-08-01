package com.promise.service;

import com.promise.dto.BaseResponse;

public interface WeatherService {
    byte[] getWeather(String key) throws Exception;
}