package com.march.common.component;


import com.march.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.swagger.models.auth.In;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录检查
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器..." + request.getRequestURI());
        String token = request.getHeader("Access-Token");
        if (token != null) {
            //传入token不为空
            Claims claims = JwtUtils.checkJWT(token);
            System.out.println(claims);
            if (claims == null) {
                //校验失败
                sendJsonMessege(response, "token无效，请重新登录!");
                return false;
            }
            //校验成功，将id和用户名放在请求参数中
            String id = claims.get("id").toString();
            String username = claims.get("username").toString();
            request.setAttribute("id", id);
            request.setAttribute("username", username);
            System.out.println(id + " " + username);
            return true;
        }
        sendJsonMessege(response, "token为null，请先登录!");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 返回前台Json数据
     *
     * @param response
     * @param msg
     * @throws IOException
     */
    public void sendJsonMessege(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        JSONObject object = new JSONObject();
        object.put("code", "-1");
        object.put("msg", msg);
        writer.write(object.toString());
    }
}
