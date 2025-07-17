package com.promise.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TravelResult {
    private Integer code;
    private Object data;
    private String msg;
}
