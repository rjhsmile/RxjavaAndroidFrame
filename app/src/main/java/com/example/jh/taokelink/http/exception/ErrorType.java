package com.example.jh.taokelink.http.exception;

/**
 * 错误类型
 *
 * @author gc
 * @since 1.0
 */
public interface ErrorType {
    /**
     * 请求成功
     */
    int SUCCESS = 200;
    /**
     * 没有登录无权限
     */
    int UNAUTHORIZED = 401;
    /**
     * 未知错误
     */
    int UNKONW = 1000;
    /**
     * 解析错误
     */
    int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    int NETWORK_ERROR = 1002;
    /**
     * HTTP请求错误
     */
    int HTTP_ERROR = 1003;
    /**
     * 解析对象为空
     */
    int EMPTY_BEAN = 1004;
    /**
     * 其他客户端登陆
     */
    int OTHER_LOGIN = 410;

    // 对应HTTP的状态码
    int FAIL = 0;
    int FORBIDDEN = 403;
    int NOT_FOUND = 404;
    int REQUEST_TIMEOUT = 408;
    int INTERNAL_SERVER_ERROR = 500;
    int BAD_GATEWAY = 502;
    int SERVICE_UNAVAILABLE = 503;
    int GATEWAY_TIMEOUT = 504;
}