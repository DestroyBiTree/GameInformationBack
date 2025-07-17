package com.promise.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.promise.mapper.GameDao;
import com.promise.entity.Game;
import com.promise.service.GameService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl extends ServiceImpl<GameDao, Game> implements GameService {
} 