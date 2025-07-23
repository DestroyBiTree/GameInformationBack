package com.promise.controller;

import com.promise.dto.BaseResponse;
import com.promise.util.HZKFont;
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
     *
     * @return BaseResponse
     */
    @GetMapping("/getWeather")
    public BaseResponse getWeather(@RequestParam String key) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("city", "610112");
        params.put("extensions", "base");
        Map<String, Object> stringObjectMap = HttpClientUtil
                .sendGetRequest("https://restapi.amap.com/v3/weather/weatherInfo", params);

        return BaseResponse.success("success", stringObjectMap);
    }

    @GetMapping("/testGetWeather")
    public BaseResponse testGetWeather(@RequestParam String key) throws Exception {

        String s = "你好解决";
        byte[][] fontDataAsJson = HZKFont.getFontData(s);
        System.out.println(fontDataAsJson);


        // 示例：获取字符串点阵数据

        return BaseResponse.success("success",fontDataAsJson);
    }
    @GetMapping("/font-data")
    public byte[] getFontData() throws Exception {
        String s = "一二三四五六七八九十甲乙丙丁戊己庚辛壬癸";
        byte[][] FONT_DATA = HZKFont.getFontData(s);
        // 将二维数组转换为一维字节数组
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