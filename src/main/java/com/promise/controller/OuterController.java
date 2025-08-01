package com.promise.controller;

import com.promise.service.FontService;
import com.promise.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin
@RestController
@RequestMapping("/outer")
public class OuterController {
     @Autowired
     WeatherService weatherService;
     @Autowired
     FontService fontService;


    @GetMapping("/getWeather")
    public byte[] getWeather(@RequestParam String key) throws Exception {
        return weatherService.getWeather(key);
    }

    @GetMapping("/font-data")
    public byte[] getFontData() throws Exception {
        String s = "";
        return fontService.getFontData(s);
    }
}