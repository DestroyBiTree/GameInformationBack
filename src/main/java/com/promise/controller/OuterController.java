package com.promise.controller;

import com.promise.dto.BaseResponse;
import com.promise.util.HttpClientUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/outer")
public class OuterController {

    /**
     * 无需登录的外部接口
     * @return BaseResponse
     */
    @GetMapping("/getWeather")
    public BaseResponse getWeather(@RequestParam String key) {

        HashMap <String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("city","610112");
        params.put("extensions","base");
        Map<String, Object> stringObjectMap = HttpClientUtil
                .sendGetRequest("https://restapi.amap.com/v3/weather/weatherInfo", params);

        return BaseResponse.success("外部访问成功", stringObjectMap);
    }
}