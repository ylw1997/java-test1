package com.yangliwei.test1.common;

import javax.servlet.http.HttpServletResponse;

/**
 *  WebUtil
 * @author yangliwei
 */
public class WebUtil {

    /**
     *  渲染返回字符串
     * @param response HttpServletResponse
     * @param string 返回字符串
     * @return void
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
