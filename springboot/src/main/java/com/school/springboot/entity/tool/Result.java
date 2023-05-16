package com.school.springboot.entity.tool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 统一返回类
 * 
 * @author 陈铕为
 * @version 0.1
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

    // 返回的响应号 200-ok
    private int status;
    // 返回的响应消息
    private Object msg;
    // 返回的响应数据
    private Object Data;

    public static Result success(int status, String msg, Object data) {
        Result result = new Result();
        result.setStatus(status);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result success(Object data) {
        return success(200, "操作成功", data);
    }

    public static Result fail(int status, Object msg, Object data) {
        Result result = new Result();
        result.setStatus(status);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result fail(Object msg, Object data) {
        return fail(500, msg, data);
    }

    public static Result fail(Object msg) {
        return fail(500, msg, null);
    }

}
