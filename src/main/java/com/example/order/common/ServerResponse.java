package com.example.order.common;

/**
 * 返回信息
 */
public class ServerResponse<T> {

    private String code;
    private String msg;
    private T data;

    private ServerResponse(String code) {
        this.code = code;
    }

    private ServerResponse(String code, T data) {
        this.code = code;
        this.data = data;
    }

    private ServerResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> com.example.order.common.ServerResponse<T> createBySuccess() {
        return new com.example.order.common.ServerResponse<T>("SUCCESS");
    }

    public static <T> com.example.order.common.ServerResponse<T> createBySuccessMessage(String msg) {
        return new com.example.order.common.ServerResponse<T>("SUCCESS", msg);
    }

    public static <T> com.example.order.common.ServerResponse<T> createBySuccess(T data) {
        return new com.example.order.common.ServerResponse<T>("SUCCESS", data);
    }

    public static <T> com.example.order.common.ServerResponse<T> createBySuccess(String msg, T data) {
        return new com.example.order.common.ServerResponse<T>("SUCCESS", msg, data);
    }

    public static <T> com.example.order.common.ServerResponse<T> createByError() {
        return new com.example.order.common.ServerResponse<T>("FAIL", "失败");
    }

    public static <T> com.example.order.common.ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new com.example.order.common.ServerResponse<T>("FAIL", errorMessage);
    }

    public static <T> com.example.order.common.ServerResponse<T> createByErrorCodeMessage(String errorCode, String errorMessage) {
        return new com.example.order.common.ServerResponse<T>(errorCode, errorMessage);
    }

    public static <T> com.example.order.common.ServerResponse<T> createBySmsCode() {
        return new com.example.order.common.ServerResponse<T>("smsCode", "请输入短信验证码！！");
    }

    public static <T> com.example.order.common.ServerResponse<T> createBySmsCode(T data) {
        return new com.example.order.common.ServerResponse<T>("smsCode", data);
    }

    public static <T> com.example.order.common.ServerResponse<T> createBySmsCode(String msg, T data) {
        return new com.example.order.common.ServerResponse<T>("smsCode", msg, data);
    }

    public static <T> com.example.order.common.ServerResponse<T> createBySmsCodeMessage(String msg) {
        return new com.example.order.common.ServerResponse<T>("smsCode", msg);
    }

    public static <T> com.example.order.common.ServerResponse<T> createByLinkInteraction(String msg, T link) {
        return new com.example.order.common.ServerResponse<T>("linkInteraction", msg, link);
    }

    public static <T> com.example.order.common.ServerResponse<T> createByHistorySuccess(String msg) {
		return new com.example.order.common.ServerResponse<T>("HISTORY_SUCCESS", msg);
	}

    public static <T> com.example.order.common.ServerResponse<T> createByExistenceSuccess(String msg) {
        return new com.example.order.common.ServerResponse<T>("EXISTENCE_SUCCESS", msg);
    }

    public static <T> com.example.order.common.ServerResponse<T> createByNotLogin() {
        return new com.example.order.common.ServerResponse<T>("notLogin", "您未登录！！");
    }

    public static <T> com.example.order.common.ServerResponse<T> createByNotLoginMessage(String msg) {
        return new com.example.order.common.ServerResponse<T>("notLogin", msg);
    }

    public static <T> com.example.order.common.ServerResponse<T> createSuccess(String code, String msg, T data) {
        return new com.example.order.common.ServerResponse<T>(code, msg,data);
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
