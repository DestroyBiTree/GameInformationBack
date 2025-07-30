package com.promise.controller;

import com.promise.dto.BaseResponse;
import com.promise.entity.WeatherInfo;
import com.promise.util.HZKFont;
import com.promise.util.HttpClientUtil;
import com.promise.util.WeatherUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

@CrossOrigin
@RestController
@RequestMapping("/outer")


public class OuterController {


    private final Deque<WeatherInfo> weatherQueue = new ConcurrentLinkedDeque<>();


    @GetMapping("/getWeather")
    public BaseResponse getWeather(@RequestParam String key) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("city", "610112");
        params.put("extensions", "base");
        Map<String, Object> stringObjectMap = HttpClientUtil
                .sendGetRequest("https://restapi.amap.com/v3/weather/weatherInfo", params);

        WeatherInfo newWeatherInfo = WeatherUtil.extractWeatherInfo(stringObjectMap);
        if (newWeatherInfo != null) {
            synchronized (weatherQueue) {
                // 移除旧数据
                if (!weatherQueue.isEmpty()) {
                    weatherQueue.poll();
                }
                // 添加新数据到队列
                weatherQueue.offer(newWeatherInfo);
            }
            return BaseResponse.success("数据更新成功", newWeatherInfo);
        } else {
            // 返回队列中现有数据（如果有）
            WeatherInfo currentWeather = weatherQueue.peek();
            if (currentWeather != null) {
                return BaseResponse.success("使用现有缓存数据", currentWeather);
            } else {
                return BaseResponse.error("暂无缓存");
            }
        }
    }

    @GetMapping("/font-data")
    public byte[] getFontData() throws Exception {
        String s = "未央晴天三八北方现在";
        byte[][] FONT_DATA = HZKFont.getFontData(s);
        byte[] result = new byte[FONT_DATA.length * FONT_DATA[0].length];
        int index = 0;
        for (byte[] row : FONT_DATA) {
            for (byte b : row) {
                result[index++] = b;
            }
        }
        return result;
    }
}