package com.li.template.exception;

import com.li.template.entry.R;
import com.nimbusds.jose.shaded.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.io.IOException;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        R r = Translator(authException);
        Gson gson = new Gson();
        String jsonResult = gson.toJson(r);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(jsonResult);
    }

    private R Translator(Exception e){
        if (e instanceof InsufficientAuthenticationException) {
            return R.error("请先登录！",401);
        }else if(e instanceof AccessDeniedException){
            return R.error("权限不足！",403);
        }else if(e instanceof MyAuthenticationException){
            return R.error(e.getMessage(),401);
        }
        return R.error("验证失败！",400);
    }
}