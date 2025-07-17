package com.promise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.promise.entity.Game;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GameDao extends BaseMapper<Game> {
} 