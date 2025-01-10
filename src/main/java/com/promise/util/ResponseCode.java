package com.promise.util;

import java.util.Arrays;

/**
 * @ClassName ResponseCode
 * @Description TODO
 * @Author lizh-w
 * @Date 2025/1/9 15:07
 * @Version 1.0
 */
public enum ResponseCode {
    /*
    请求返回状态码和说明信息
     */
    SUCCESS(200, "成功"), BAD_REQUEST(400, "参数或者语法不对"), UNAUTHORIZED(401, "登录失败，用户信息异常"),
    LOGIN_ERROR(401, "登录失败，用户名或密码错误"), FORBIDDEN(403, "没有权限"), TOKEN_EXPIRED(403, "token过期"), NO_TOKEN(403, "token缺失"),
    INVALID_TOKEN(403, "token无效"), NOT_FOUND(404, "请求的资源不存在"), TIME_OUT(408, "请求超时"), FAIL(417, "失败"),
    SERVER_ERROR(500, "服务器内部错误"), SERVER_UNAVALIABLE(503, "服务不可用"),
    JENKINS_APITOKEM_CREATE_ERROR(504, "Jenkins ApiToken生成异常，请联系管理员！"), NO_WORKFLOWTYPE(400, "参数错误，必须选择单别！"),
    OTHER(0, "其他原因"),

    PRODUCT_CRASH_UN_SETTING(421, "改产品未配置崩溃率"), DARA_REPEAR(422, "数据重复"), SERVER_RPC_ERROR(505, "远程调用失败"),
    ILLEGAL_CHARACTERS(400, "输入数据中存在非法字符！");

    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMessage(int code) {
        ResponseCode founded =
                Arrays.stream(ResponseCode.values()).filter(n -> n.getCode() == code).findFirst().orElse(null);
        return founded == null ? "" : founded.getMsg();
    }

    public static ResponseCode getInstanceByCode(Integer code) {
        return Arrays.stream(ResponseCode.values()).filter(n -> n.getCode() == code).findFirst().orElse(OTHER);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

