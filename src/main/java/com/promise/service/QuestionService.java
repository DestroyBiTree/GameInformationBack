package com.promise.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.promise.entity.Question;

/**
 * 问题服务接口
 */
public interface QuestionService extends IService<Question> {
    
    /**
     * 更新问题的回答状态
     * @param id 问题ID
     * @param answered 回答状态
     * @return 是否更新成功
     */
    boolean updateAnswered(Integer id, Integer answered);
} 