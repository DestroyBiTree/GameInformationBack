package com.promise.service.Impl;

import com.promise.annotation.CustomLogging;
import com.promise.entity.DiscountInformation;
import com.promise.mapper.DiscountMapper;
import com.promise.service.DiscountService;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    KieSession kieSession;

    @CustomLogging(value = "searchAllDiscount", customValue = "searchAllDiscountCustom")
    @Override
    public String searchAllDiscount(String userName) {
        List<DiscountInformation> allInformation = discountMapper.getAllInformation();
        String result = allInformation.toString();
        return result;

    }
    @CustomLogging(value = "getInformationById", customValue = "getInformationByIdCustom")
    @Override
    public String getInformationById(Integer userId) {
        String result;
        DiscountInformation informationById = discountMapper.getInformationById(userId);
        kieSession.insert(informationById);
        kieSession.fireAllRules();
        System.out.println(">>>>>" + informationById.getPrice());
        return null;


    }

}
