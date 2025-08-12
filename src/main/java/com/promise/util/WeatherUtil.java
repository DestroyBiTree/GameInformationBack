package com.promise.util;

import com.promise.entity.WeatherInfo;

import java.util.List;
import java.util.Map;

public class WeatherUtil {

    /**
     * 从高德天气API返回的Map中提取天气信息（修正后JSON结构）
     * @param responseMap API返回的原始Map数据（无外层data字段）
     * @return 封装后的天气信息对象，失败返回null
     */
    public static WeatherInfo extractWeatherInfo(Map<String, Object> responseMap) {
        try {
            // 检查接口状态是否正常（status为"1"表示成功）
            String status = (String) responseMap.get("status");
            if (!"1".equals(status)) {
                String info = (String) responseMap.get("info");
                String infocode = (String) responseMap.get("infocode");
                return null;
            }

            // 获取lives数组（天气详情列表）
            List<Map<String, Object>> livesList = (List<Map<String, Object>>) responseMap.get("lives");
            if (livesList == null || livesList.isEmpty()) {
                System.out.println("未获取到天气数据（lives列表为空）");
                return null;
            }

            Map<String, Object> weatherData = livesList.get(0);

            WeatherInfo weatherInfo = new WeatherInfo();
            weatherInfo.setProvince((String) weatherData.get("province"));       // 省份
            weatherInfo.setCity((String) weatherData.get("city"));               // 城市/区域
            weatherInfo.setAdcode((String) weatherData.get("adcode"));           // 区域编码
            weatherInfo.setWeather((String) weatherData.get("weather"));         // 天气状况
            weatherInfo.setTemperature((String) weatherData.get("temperature")); // 温度
            weatherInfo.setWindDirection((String) weatherData.get("winddirection")); // 风向
            weatherInfo.setWindPower((String) weatherData.get("windpower"));     // 风力
            weatherInfo.setHumidity((String) weatherData.get("humidity"));       // 湿度
            weatherInfo.setReportTime((String) weatherData.get("reporttime"));   // 数据更新时间
            weatherInfo.setSendingFrequency((String) weatherData.get("sendingFrequency"));   // 发送频率
            weatherInfo.setTemperatureFloat((String) weatherData.get("temperature_float")); // 温度（浮点型）
            weatherInfo.setHumidityFloat((String) weatherData.get("humidity_float"));     // 湿度（浮点型）

            return weatherInfo;

        } catch (ClassCastException e) {
            System.err.println("数据类型转换失败：" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("提取天气数据时发生错误：" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}