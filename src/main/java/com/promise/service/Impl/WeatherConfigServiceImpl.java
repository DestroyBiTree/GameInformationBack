package com.promise.service.Impl;

import com.promise.entity.WeatherConfig;
import com.promise.mapper.WeatherDao;
import com.promise.service.WeatherConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class WeatherConfigServiceImpl implements WeatherConfigService {

    @Autowired
    private WeatherDao weatherDao;

    @Override
    public List<WeatherConfig> getWeatherConfig(Set<Integer> ids) {
        return weatherDao.getWeatherConfig(ids);
    }

    @Override
    public boolean updateWeatherConfig(WeatherConfig weatherConfig) {
        try {
            weatherDao.updateWeatherConfig(weatherConfig);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}