package com.promise.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.promise.mapper.QuestionDao;
import com.promise.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, Question> {
    @Autowired
    private QuestionDao questionDao;

    public boolean updateAnswered(Integer id, Integer answered) {
        return questionDao.updateAnswered(id, answered) > 0;
    }
}
