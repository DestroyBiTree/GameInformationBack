package com.promise.entity;

import lombok.Data;

/**
 * @ClassName DiscountInformation
 * @Description TODO
 * @Author lizh-w
 * @Date 2025/1/9 16:16
 * @Version 1.0
 */
@Data
public class DiscountInformation {
    // 主键id
    private Integer id;
    // 提问的用户的id
    private Integer userId;
    // 提问内容
    private String content;

    private Integer price;

}
