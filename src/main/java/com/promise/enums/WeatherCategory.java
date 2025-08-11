package com.promise.enums;

import java.util.HashMap;
import java.util.Map;

public enum WeatherCategory {
    // 两字大类枚举(附带数字编码0-8)
    晴朗("晴朗", 0),
    多云("多云", 1),
    有风("有风", 2),
    雾霾("雾霾", 3),
    降雨("降雨", 4),
    降雪("降雪", 5),
    特殊("特殊", 6);

    private final String categoryName;
    private final int categoryCode;
    private static final Map<String, WeatherCategory> weatherMap = new HashMap<>();

    // 静态初始化块，建立天气类型到大类的映射
    static {
        // 晴好天气
        weatherMap.put("晴", 晴朗);
        weatherMap.put("少云", 晴朗);
        weatherMap.put("晴间多云", 晴朗);

        // 多云/阴天
        weatherMap.put("多云", 多云);
        weatherMap.put("阴", 多云);

        // 风力天气
        weatherMap.put("有风", 有风);
        weatherMap.put("平静", 有风);
        weatherMap.put("微风", 有风);
        weatherMap.put("和风", 有风);
        weatherMap.put("清风", 有风);
        weatherMap.put("强风/劲风", 有风);
        weatherMap.put("疾风", 有风);
        weatherMap.put("大风", 有风);
        weatherMap.put("烈风", 有风);
        weatherMap.put("风暴", 有风);
        weatherMap.put("狂爆风", 有风);
        weatherMap.put("飓风", 有风);
        weatherMap.put("热带风暴", 有风);

        // 雾霾天气
        weatherMap.put("霾", 雾霾);
        weatherMap.put("中度霾", 雾霾);
        weatherMap.put("重度霾", 雾霾);
        weatherMap.put("严重霾", 雾霾);

        // 降雨天气
        weatherMap.put("阵雨", 降雨);
        weatherMap.put("雷阵雨", 降雨);
        weatherMap.put("雷阵雨并伴有冰雹", 降雨);
        weatherMap.put("小雨", 降雨);
        weatherMap.put("中雨", 降雨);
        weatherMap.put("大雨", 降雨);
        weatherMap.put("暴雨", 降雨);
        weatherMap.put("大暴雨", 降雨);
        weatherMap.put("特大暴雨", 降雨);
        weatherMap.put("强阵雨", 降雨);
        weatherMap.put("强雷阵雨", 降雨);
        weatherMap.put("极端降雨", 降雨);
        weatherMap.put("毛毛雨/细雨", 降雨);
        weatherMap.put("雨", 降雨);
        weatherMap.put("小雨-中雨", 降雨);
        weatherMap.put("中雨-大雨", 降雨);
        weatherMap.put("大雨-暴雨", 降雨);
        weatherMap.put("暴雨-大暴雨", 降雨);
        weatherMap.put("大暴雨-特大暴雨", 降雨);

        // 降雪天气
        weatherMap.put("雨雪天气", 降雪);
        weatherMap.put("雨夹雪", 降雪);
        weatherMap.put("阵雨夹雪", 降雪);
        weatherMap.put("冻雨", 降雪);
        weatherMap.put("雪", 降雪);
        weatherMap.put("阵雪", 降雪);
        weatherMap.put("小雪", 降雪);
        weatherMap.put("中雪", 降雪);
        weatherMap.put("大雪", 降雪);
        weatherMap.put("暴雪", 降雪);
        weatherMap.put("小雪-中雪", 降雪);
        weatherMap.put("中雪-大雪", 降雪);
        weatherMap.put("大雪-暴雪", 降雪);

        // 特殊天气
        weatherMap.put("浮尘", 特殊);
        weatherMap.put("扬沙", 特殊);
        weatherMap.put("沙尘暴", 特殊);
        weatherMap.put("强沙尘暴", 特殊);
        weatherMap.put("龙卷风", 特殊);
        weatherMap.put("雾", 特殊);
        weatherMap.put("浓雾", 特殊);
        weatherMap.put("强浓雾", 特殊);
        weatherMap.put("轻雾", 特殊);
        weatherMap.put("大雾", 特殊);
        weatherMap.put("特强浓雾", 特殊);

        weatherMap.put("热", 特殊);
        weatherMap.put("冷", 特殊);

        weatherMap.put("未知", 特殊);
    }

    WeatherCategory(String categoryName, int categoryCode) {
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    // 根据天气类型获取对应的大类名称
    public static String getCategoryName(String weatherType) {
        WeatherCategory category = weatherMap.get(weatherType);
        return category != null ? category.getCategoryName() : 特殊.getCategoryName();
    }

    // 根据天气类型获取对应的大类数字编码
    public static int getCategoryCode(String weatherType) {
        WeatherCategory category = weatherMap.get(weatherType);
        return category != null ? category.getCategoryCode() : 特殊.getCategoryCode();
    }
}
