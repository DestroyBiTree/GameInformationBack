package com.promise.controller;

import com.promise.dto.BaseResponse;
import com.promise.entity.WeatherConfig;
import com.promise.service.WeatherConfigService;
import com.promise.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/weather/config")
public class WeatherConfigController {

    @Autowired
    private WeatherConfigService weatherConfigService;

    /**
     * 获取所有天气配置信息
     *
     * @return BaseResponse
     */
    @GetMapping("/getAllWeatherConfig")
    @ResponseBody
    public BaseResponse getAllWeatherConfig() {
        List<WeatherConfig> weatherConfigs = weatherConfigService.getWeatherConfig(null);
        if (weatherConfigs != null) {
            return BaseResponse.success("查询成功", weatherConfigs);
        } else {
            return BaseResponse.error("查询失败");
        }
    }

    /**
     * 根据ID获取天气配置信息
     *
     * @param id 天气配置ID
     * @return BaseResponse
     */
    @GetMapping("/{id}")
    @ResponseBody
    public BaseResponse getWeatherConfigById(@PathVariable Integer id) {
        Set<Integer> ids = new HashSet<>();
        ids.add(id);
        List<WeatherConfig> weatherConfigs = weatherConfigService.getWeatherConfig(ids);
        if (weatherConfigs != null && !weatherConfigs.isEmpty()) {
            return BaseResponse.success("查询成功", weatherConfigs.get(0));
        } else {
            return BaseResponse.error(ResponseCode.NOT_FOUND, "id不存在或查询失败");
        }
    }


    /**
     * 更新天气配置信息
     *
     * @param weatherConfig 天气配置信息
     * @return BaseResponse
     */
    @PostMapping("updateWeatherConfig")
    @ResponseBody
    public BaseResponse updateWeatherConfig(@RequestBody WeatherConfig weatherConfig) {
        boolean flag = weatherConfigService.updateWeatherConfig(weatherConfig);
        if (flag) {
            return BaseResponse.success("更新成功", true);
        } else {
            return BaseResponse.error("更新失败");
        }
    }

}
