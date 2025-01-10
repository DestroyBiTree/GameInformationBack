package com.promise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.promise.entity.DiscountInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DiscountMapper {

    DiscountInformation getAllInformation();


}
