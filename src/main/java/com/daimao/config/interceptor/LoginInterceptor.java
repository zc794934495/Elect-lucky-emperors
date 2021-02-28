package com.daimao.config.interceptor;

import com.daimao.base.JSONResponse;
import com.daimao.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {

    private ObjectMapper objectMapper;

    public LoginInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute("user");
            if (user != null) {
                return true;
            }
        }

        //请求的服务路径
        String servletPath = request.getServletPath();
        if (servletPath.startsWith("/api/")) {//后端资源
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            JSONResponse json = new JSONResponse();
            json.setCode("USR000");
            json.setMessage("用户没有登录，不允许访问");
            String s = objectMapper.writeValueAsString(json);
            //返回401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            PrintWriter pw = response.getWriter();
            pw.println(s);
            pw.flush();
        } else {//前端资源
            String schema = request.getScheme();//http
            String host = request.getServerName();//ip
            int port = request.getServerPort();//port
            String contextPath = request.getContextPath();//application Context path
            String basePath = schema + "://" + host + ":" + port + contextPath;
            //重定向到登录页面
            response.sendRedirect(basePath + "/index.html");
        }
        return false;
    }
}
