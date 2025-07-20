package com.promise.controller;

import com.promise.dto.BaseResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Queue;

@CrossOrigin
@RestController
@RequestMapping("/outer")
public class OuterController {

    /**
     * 无需登录的外部接口
     * @return BaseResponse
     */
    @GetMapping("/getWeather")
    public BaseResponse getWeather() {

        Queue<String> weatherQueue = new LinkedList<>();
        
        return BaseResponse.success("外部访问成功", null);
    }
}