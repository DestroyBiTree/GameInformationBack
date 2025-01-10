package com.promise.service.Impl;

import com.promise.mapper.DiscountMapper;
import com.promise.entity.DiscountInformation;
import com.promise.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName DiscountServiceImpl
 * @Description TODO
 * @Author lizh-w
 * @Date 2025/1/9 14:09
 * @Version 1.0
 */
@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    DiscountMapper discountMapper;

    @Override
    public String searchDiscount(String userName) {
        DiscountInformation allInformation = discountMapper.getAllInformation();
        String result = allInformation.toString();
        return result;

    }
}
