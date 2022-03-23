package com.yangliwei.test1.common;

import com.yangliwei.test1.config.error.CustomException;
import com.yangliwei.test1.config.error.CustomExceptionType;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author ylw12
 */
@Data
public class AjaxResult  {

    private int code;
    private String msg;
    private Object data;

    public AjaxResult(){}

    public AjaxResult(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(int code, String msg, Object data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功信息
     * @param msg 消息
     * @param data 数据
     * @return 成功
     */
    public static AjaxResult success (String msg,Object data){
        return new AjaxResult(HttpStatus.OK.value(),msg,data);
    }

    public static AjaxResult success (String msg){
        return AjaxResult.success(msg,null);
    }

    public static AjaxResult success (Object data){
        return AjaxResult.success("操作成功",data);
    }

    public static AjaxResult success (){
        return AjaxResult.success("操作成功!");
    }


    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(String msg, Object data)
    {
        return new AjaxResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, data);
    }

    public static AjaxResult error(int code, String msg)
    {
        return new AjaxResult(code, msg, null);
    }

    public static AjaxResult error(String msg)
    {
        return AjaxResult.error(msg, null);
    }

        public static AjaxResult error()
    {
        return AjaxResult.error("操作失败");
    }

    public static AjaxResult error(CustomExceptionType customExceptionType)
    {
        return AjaxResult.error(customExceptionType.getCode(), customExceptionType.getMessage());
    }

    public static AjaxResult error(CustomExceptionType customExceptionType, String msg)
    {
        return AjaxResult.error(customExceptionType.getCode(), msg);
    }

    public static AjaxResult error(CustomException customException){
        return AjaxResult.error(customException.getCode(),customException.getMessage());
    }

    public static AjaxResult error(CustomException customException,String msg){
        return AjaxResult.error(customException.getCode(),msg);
    }
}
