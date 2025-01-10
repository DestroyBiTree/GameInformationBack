package com.promise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.promise.entity.Travel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TravelDao extends BaseMapper<Travel> {
}
