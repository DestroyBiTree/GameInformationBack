package com.promise.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Game {
    // 主键
    private Integer id;
    // 游戏名称
    private String title;
    // 游戏平台
    private String platform;
    // 游戏描述
    private String description;
    // 游戏类型
    private String genre;
    // 评分 (1-10)
    private Double rating;
    // 回复的问题的id 无则为null
    @TableField(value = "questionID")
    private Integer questionID;
}
