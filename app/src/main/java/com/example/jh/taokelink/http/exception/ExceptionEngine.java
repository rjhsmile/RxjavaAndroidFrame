package com.example.jh.taokelink.http.exception;

import com.example.jh.taokelink.App;
import com.example.jh.taokelink.R;
import com.example.jh.taokelink.utils.ToastUtils;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

/**
 * Exception Engine
 *
 * @author gc
 * @since 1.0
 */
public class ExceptionEngine {

    public static ApiException handleException(Throwable e) {
        ApiException ex = null;
        if (e instanceof ServerException) { // HTTP错误
            ServerException httpException = (ServerException) e;
            ex = new ApiException(e, ErrorType.HTTP_ERROR);
            switch (httpException.code) {
                case ErrorType.FAIL:
//                    ex.message = "userName or passWord is error!";
                    ex.message = App.getInstance().getString(R.string.service_connect_failed);
                    break;
                case ErrorType.UNAUTHORIZED:
//                    ex.message = "当前请求需要用户验证";
                    ex.message = App.getInstance().getString(R.string.service_connect_failed);
                    break;
                case ErrorType.FORBIDDEN:
//                    ex.message = "服务器已经理解请求，但是拒绝执行它";
                    ex.message = App.getInstance().getString(R.string.service_connect_failed);
                    break;
                case ErrorType.NOT_FOUND:
//                    ex.message = "服务器异常，请稍后再试";
                    ex.message = App.getInstance().getString(R.string.service_connect_failed);
                    break;
                case ErrorType.REQUEST_TIMEOUT:
                    ex.message = App.getInstance().getString(R.string.connect_timeout);
//                    ex.message = "请求超时";
                    break;
                case ErrorType.GATEWAY_TIMEOUT:
//                    ex.message = "作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP" +
//                            "）或者辅助服务器（例如DNS）收到响应";
                    ex.message = App.getInstance().getString(R.string.server_exception);
                    break;
                case ErrorType.INTERNAL_SERVER_ERROR:
//                    ex.message = "服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理";
                    ex.message = App.getInstance().getString(R.string.server_exception);
                    break;
                case ErrorType.BAD_GATEWAY:
//                    ex.message = "作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应";
                    ex.message = App.getInstance().getString(R.string.server_exception);
                    break;
                case ErrorType.SERVICE_UNAVAILABLE:
//                    ex.message = "由于临时的服务器维护或者过载，服务器当前无法处理请求";
                    ex.message = App.getInstance().getString(R.string.server_exception);
                    break;
                default:
                    ex.message = App.getInstance().getString(R.string.server_exception); // 其它均视为网络错误
                    break;
            }
            return ex;
        } else if (e instanceof ServerException) {
            // 服务器返回的错误
            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, resultException.code);
            ex.message = resultException.message;
            return ex;
        } else if (e instanceof JSONException || e instanceof ParseException) {
            //均视为解析错误
            ex = new ApiException(e, ErrorType.PARSE_ERROR);
            ex.message = App.getInstance().getString(R.string.server_exception);
            return ex;
        } else if (e instanceof ConnectException || e instanceof SocketTimeoutException ||
                e instanceof ConnectTimeoutException || e instanceof UnknownHostException) {
            // 均视为网络错误
            ex = new ApiException(e, ErrorType.UNKONW);
            ex.message = App.getInstance().getString(R.string.server_exception);
            return ex;
        }
        return ex;
    }

}