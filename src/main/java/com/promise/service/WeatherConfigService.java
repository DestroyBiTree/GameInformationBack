package com.promise.service;

import com.promise.entity.WeatherConfig;

import java.util.List;
import java.util.Set;

/**
 * 天气配置服务接口
 */
public interface WeatherConfigService {
    
    /**
     * 获取天气配置信息
     * @param ids 天气配置ID集合，如果为空则获取所有配置
     * @return 天气配置列表
     */
    List<WeatherConfig> getWeatherConfig(Set<Integer> ids);
    
    /**
     * 更新天气配置信息
     * @param weatherConfig 天气配置信息
     * @return 是否更新成功
     */
    boolean updateWeatherConfig(WeatherConfig weatherConfig);


}