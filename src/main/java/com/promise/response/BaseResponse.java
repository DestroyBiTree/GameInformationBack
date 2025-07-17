package com.promise.response;

import com.promise.util.ResponseCode;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BaseResponse
 * @Description TODO
 * @Author lizh-w
 * @Date 2025/1/9 15:05
 * @Version 1.0
 */
@Data

public class BaseResponse {
    private static final long serialVersionUID = 1L;

    /**
     * 结果状态码(详见 com.glodon.lingbo.utils.ResponseCode 状态信息配置类)
     */
    private int code;
    /**
     * 响应结果描述
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

    public BaseResponse() {
        super();
    }

    /**
     * @param code 结果状态码(详见 com.glodon.lingbo.utils.ResponseCode 状态信息配置类)
     * @param message 响应结果描述
     */
    public BaseResponse(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    /**
     * @param code 结果状态码(详见 com.glodon.lingbo.utils.ResponseCode 状态信息配置类)
     * @param message 响应结果描述
     * @param data 数据
     */
    public BaseResponse(int code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 结果状态码
     */
    public int getCode() {
        return code;
    }

    /**
     * 结果状态码
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 响应结果描述
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置响应结果描述
     *
     * @param message 响应结果描述
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static BaseResponse successObject(Object data) {
        return BaseResponse.build(ResponseCode.SUCCESS, data);
    }

    public static BaseResponse success() {
        return BaseResponse.build(ResponseCode.SUCCESS, "");
    }

    public static BaseResponse build(ResponseCode code, Object data) {
        return new BaseResponseBuilder().code(code.getCode()).message(code.getMsg()).data(data).build();
    }

    public static BaseResponseBuilder buildResponse() {
        return new BaseResponseBuilder();
    }

    public static final class BaseResponseBuilder {
        private static final Map<Object, Object> NULL_OBJ = new HashMap<>();
        /**
         * 返回值
         */
        private int retCode;
        /**
         * msg
         */
        private String message;

        /**
         * 数据对象
         */
        private Object data;

        private BaseResponseBuilder() {}

        public BaseResponseBuilder code(ResponseCode code) {
            this.retCode = code.getCode();
            this.message = code.getMsg();
            return this;
        }

        public BaseResponseBuilder code(int code) {
            this.retCode = code;
            this.message = ResponseCode.getMessage(code);
            return this;
        }

        public BaseResponseBuilder setCode(ResponseCode code, String msg) {
            this.retCode = code.getCode();
            this.message = msg;
            return this;
        }

        public BaseResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * 设置result obj
         *
         * @param obj
         * @return
         */
        public BaseResponseBuilder data(final Object obj) {
            this.data = obj;
            return this;
        }

        /**
         * build BaseResponse
         *
         * @return
         */
        public BaseResponse build() {
            return new BaseResponse(this.retCode, this.message, this.data == null ? NULL_OBJ : this.data);
        }
    }
}
