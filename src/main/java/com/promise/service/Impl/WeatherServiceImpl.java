package com.promise.service.Impl;

import com.promise.dto.BaseResponse;
import com.promise.entity.WeatherConfig;
import com.promise.entity.WeatherInfo;
import com.promise.enums.WeatherCategory;
import com.promise.service.FontService;
import com.promise.service.WeatherConfigService;
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

    @Autowired
    WeatherConfigService weatherConfigService;

    @Override
    public byte[] getWeather(String boardId) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        WeatherConfig weatherConfig = weatherConfigService.getWeatherConfig(
                Collections.singleton(Integer.parseInt(boardId))).get(0);
        params.put("key", weatherConfig.getApiKey());
        params.put("city", weatherConfig.getLocalCode());
        params.put("extensions", "base");
//        Map<String, Object> weatherInforMap = HttpClientUtil
//                .sendGetRequest("https://restapi.amap.com/v3/weather/weatherInfo", params);
//        weatherInforMap.put("sendingFrequency",Integer.toString(weatherConfig.getSendingFrequency()));

        WeatherInfo weatherInfo = WeatherUtil.extractWeatherInfo(buildFakeWeatherDataMap(weatherConfig));
//        WeatherInfo weatherInfo = WeatherUtil.extractWeatherInfo(weatherInforMap);
        if (weatherInfo != null) {
            synchronized (weatherQueue) {
                if (!weatherQueue.isEmpty()) {
                    weatherQueue.poll();
                }
                weatherQueue.offer(weatherInfo);
            }
            return buildResponseString(weatherInfo);
        } else {
            WeatherInfo currentWeather = weatherQueue.peek();
            if (currentWeather != null) {
                return buildResponseString(currentWeather);
            } else {
                return null;
            }
        }
    }

    public byte[] buildResponseString(WeatherInfo resWeatherInfo) throws Exception {
        // 天气码
        int categoryCode = WeatherCategory.getCategoryCode(resWeatherInfo.getWeather());
        String categoryName = WeatherCategory.getCategoryName(resWeatherInfo.getWeather());
        byte[] fontData = fontService.getFontData(categoryName);
        int tem = Integer.parseInt(resWeatherInfo.getTemperature());
        int sendingFrequency = Integer.parseInt(resWeatherInfo.getSendingFrequency());


        // 验证数据有效性
        if (categoryCode < 0 || categoryCode > 9) {
            throw new IllegalArgumentException("categoryCode must be between 0 and 9");
        }
        if (fontData == null || fontData.length != 64) {
            throw new IllegalArgumentException("fontData must be 64 bytes long");
        }
        if (tem < -20 || tem > 50) {
            throw new IllegalArgumentException("tem must be between -20 and 50");
        }

        // 创建固定大小的字节数组 (1 + 64 + 1 = 66)
        byte[] result = new byte[67];

        // 放入categoryCode (1字节)
        result[0] = (byte) categoryCode;

        // 放入fontData (64字节)
        System.arraycopy(fontData, 0, result, 1, 64);

        // 放入tem (1字节)
        result[65] = (byte) tem;
        result[66] = (byte) sendingFrequency;

        return result;
    }

    public static Map<String, Object> buildFakeWeatherDataMap(WeatherConfig weatherConfig) {
        // 1. 构建实时天气数据
        Map<String, Object> liveWeather = new HashMap<>();
        liveWeather.put("province", "陕西");
        liveWeather.put("city", "未央");
        liveWeather.put("adcode", "610112");
        liveWeather.put("weather", "晴");
        liveWeather.put("temperature", "30");
        liveWeather.put("winddirection", "西南");
        liveWeather.put("windpower", "≤3");
        liveWeather.put("humidity", "41");
        liveWeather.put("reporttime", "2025-07-31 00:38:10");
        liveWeather.put("temperature_float", "33.0");
        liveWeather.put("humidity_float", "41.0");
        liveWeather.put("sendingFrequency",Integer.toString(weatherConfig.getSendingFrequency()));

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