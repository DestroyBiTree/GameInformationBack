package com.promise.mapper;

import com.promise.entity.WeatherConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface WeatherDao {

    List<WeatherConfig> getWeatherConfig(@Param("ids") Set ids);

    void updateWeatherConfig(WeatherConfig weatherConfig);

}
