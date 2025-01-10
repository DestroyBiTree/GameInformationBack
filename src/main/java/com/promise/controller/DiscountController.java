package com.promise.controller;

import com.promise.result.BaseResponse;
import com.promise.result.QuestionResult;
import com.promise.service.DiscountService;
import com.promise.util.ResponseCode;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName DiscountController
 * @Description TODO
 * @Author lizh-w
 * @Date 2025/1/9 14:06
 * @Version 1.0
 */
@CrossOrigin
@RestController
@Validated
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    DiscountService discountService;
    @GetMapping("/searchDiscount/{userName}")
    public BaseResponse searchDiscount(
            @ApiParam(value = "用户名字", required = true) @PathVariable(value = "userName")  String userName){
        String response = discountService.searchAllDiscount(userName);
        return BaseResponse.successObject(response);
    }
    @GetMapping("/searchDiscountById/{userId}")
    public BaseResponse getInformationById(
            @ApiParam(value = "用户id", required = true) @PathVariable(value = "userId")  Integer userId){
        String response = discountService.getInformationById(userId);
        return BaseResponse.successObject(response);
    }


}
