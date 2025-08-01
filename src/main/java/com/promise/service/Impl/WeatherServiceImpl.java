package com.promise.service.Impl;

import com.promise.dto.BaseResponse;
import com.promise.entity.WeatherInfo;
import com.promise.service.FontService;
import com.promise.service.WeatherService;
import com.promise.util.HttpClientUtil;
import com.promise.util.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final Deque<WeatherInfo> weatherQueue = new ConcurrentLinkedDeque<>();
    @Autowired
    FontService fontService;

    @Override
    public byte[] getWeather(String key) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("city", "610112");
        params.put("extensions", "base");
//        Map<String, Object> stringObjectMap = HttpClientUtil
//                .sendGetRequest("https://restapi.amap.com/v3/weather/weatherInfo", params);

        WeatherInfo newWeatherInfo = WeatherUtil.extractWeatherInfo(buildFakeWeatherDataMap());
        if (newWeatherInfo != null) {
            synchronized (weatherQueue) {
                if (!weatherQueue.isEmpty()) {
                    weatherQueue.poll();
                }
                weatherQueue.offer(newWeatherInfo);
            }
            System.out.println(buildResponseString(newWeatherInfo));
            return fontService.getFontData(buildResponseString(newWeatherInfo));
        } else {
            WeatherInfo currentWeather = weatherQueue.peek();
            if (currentWeather != null) {
                return fontService.getFontData(buildResponseString(currentWeather));
            } else {
                return null;
            }
        }
    }

    public static String buildResponseString(WeatherInfo resWeatherInfo){
        StringBuilder res = new StringBuilder();
        res.append(resWeatherInfo.getCity());
        res.append(resWeatherInfo.getWeather());
        res.append(resWeatherInfo.getWindDirection());
        res.append(resWeatherInfo.getWindDirection());
        res.append(resWeatherInfo.getWindDirection());
        String result = res.toString();
        return result;

    }

    public static Map<String, Object> buildFakeWeatherDataMap() {
        // 1. 构建实时天气数据
        Map<String, Object> liveWeather = new HashMap<>();
        liveWeather.put("province", "陕西");
        liveWeather.put("city", "未央");
        liveWeather.put("adcode", "610112");
        liveWeather.put("weather", "晴天");
        liveWeather.put("temperature", "33");
        liveWeather.put("winddirection", "西南");
        liveWeather.put("windpower", "≤3");
        liveWeather.put("humidity", "41");
        liveWeather.put("reporttime", "2025-07-31 00:38:10");
        liveWeather.put("temperature_float", "33.0");
        liveWeather.put("humidity_float", "41.0");

        // 2. 将实时天气数据放入lives列表
        List<Map<String, Object>> livesList = new ArrayList<>();
        livesList.add(liveWeather);

        // 3. 构建顶层数据
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", "1");
        resultMap.put("count", "1");
        resultMap.put("info", "OK");
        resultMap.put("infocode", "10000");
        resultMap.put("lives", livesList);

        return resultMap;
    }
}