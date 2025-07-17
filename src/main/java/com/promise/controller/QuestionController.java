package com.promise.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.promise.dto.BaseResponse;
import com.promise.entity.Question;
import com.promise.entity.Game;
import com.promise.service.QuestionService;
import com.promise.service.GameService;
import com.promise.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private GameService gameService;

    /**
     * 分页查询所有用户的问题
     *
     * @return BaseResponse
     */
    @GetMapping("/{currentPage}/{pageSize}")
    @ResponseBody
    public BaseResponse getQuestionByPage(@PathVariable Integer currentPage,
                                            @PathVariable Integer pageSize) {
        IPage<Question> iPage = new Page<>(currentPage, pageSize);
        IPage<Question> page = questionService.page(iPage, null);
        
        if (page.getRecords() != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("pages", page.getPages());
            data.put("currentPage", page.getCurrent());
            data.put("pageSize", page.getSize());
            data.put("questions", page.getRecords());
            data.put("total", page.getTotal());
            return BaseResponse.success("查询成功", data);
        } else {
            return BaseResponse.error("查询失败");
        }
    }

    /**
     * 添加问题
     *
     * @param question 问题
     * @return BaseResponse
     */
    @PostMapping
    @ResponseBody
    public BaseResponse save(@RequestBody Question question) {
        boolean flag = questionService.save(question);
        if (flag) {
            return BaseResponse.success("添加成功", true);
        } else {
            return BaseResponse.error("添加失败");
        }
    }

    /**
     * 根据id删除问题
     *
     * @param id 问题的id
     * @return BaseResponse
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    public BaseResponse delete(@PathVariable Integer id) {
        boolean flag = questionService.removeById(id);
        if (flag) {
            return BaseResponse.success("删除成功", true);
        } else {
            return BaseResponse.error("删除失败");
        }
    }

    /**
     * 根据id更新问题信息
     *
     * @param id       问题的id
     * @param question 问题信息
     * @return BaseResponse
     */
    @PutMapping("/{id}")
    @ResponseBody
    public BaseResponse updateQuestion(@PathVariable Integer id, @RequestBody Question question) {
        question.setId(id);
        boolean flag = questionService.updateById(question);
        if (flag) {
            return BaseResponse.success("更新成功", true);
        } else {
            return BaseResponse.error("更新失败");
        }
    }

    /**
     * 更新问题的answered字段
     *
     * @param id       问题的id
     * @param answered 问题的answered字段
     * @return BaseResponse
     */
    @PutMapping("/{id}/{answered}")
    @ResponseBody
    public BaseResponse updateAnswered(@PathVariable Integer id, @PathVariable Integer answered) {
        boolean flag = questionService.updateAnswered(id, answered);
        if (flag) {
            return BaseResponse.success("更新成功", true);
        } else {
            return BaseResponse.error("更新失败");
        }
    }

    /**
     * 根据用户ID查询问题
     *
     * @param userId 用户的id
     * @return BaseResponse
     */
    @GetMapping("/user/{userId}")
    @ResponseBody
    public BaseResponse getQuestionsByUserId(@PathVariable Integer userId) {
        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Question::getUserID, userId);
        List<Question> questions = questionService.list(queryWrapper);
        if (questions != null) {
            return BaseResponse.success("查询成功", questions);
        } else {
            return BaseResponse.error("查询失败");
        }
    }
}
