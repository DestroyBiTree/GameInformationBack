package com.promise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.promise.entity.DiscountInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscountMapper {

    List<DiscountInformation> getAllInformation();
    DiscountInformation getInformationById(@Param("userId") Integer id);


}
