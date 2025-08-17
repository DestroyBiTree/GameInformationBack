package com.promise.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SunSimpleController {

    @GetMapping("/states/{entityId}")
    public Map<String, Object> getSunDataWithMap(@PathVariable String entityId) {
        // 创建外层Map
        Map<String, Object> result = new HashMap<>();

// 创建attributes嵌套Map
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("current_temp", 20.3);
        attributes.put("targer_temp", 25);
        attributes.put("fan_mode", "High");
        attributes.put("operation_state", "cooling");
        attributes.put("daily_use", 0.958);
        attributes.put("electric_power", 10);
        attributes.put("icon", "mdi:air-conditioner");
        attributes.put("friendly_name", "air_conditioner");

// 组装所有字段
        result.put("attributes", attributes);
        result.put("entity_id", "sensor.kong_diao_zong_he_jian_kong");
        result.put("state", "cool");
        result.put("last_changed", "2025-08-14T15:11:08.283515+00:00");
        result.put("last_reported", "2025-08-14T15:12:08.178883+00:00");
        result.put("last_updated", "2025-08-14T15:12:08.178883+00:00");

// 创建context嵌套Map
        Map<String, Object> context = new HashMap<>();
        context.put("id", "01K2MJ6VFD680S7Q44QK8A0GXB");
        context.put("parent_id", null);
        context.put("user_id", null);

// 将context放入外层Map
        result.put("context", context);

        return result;
    }
}