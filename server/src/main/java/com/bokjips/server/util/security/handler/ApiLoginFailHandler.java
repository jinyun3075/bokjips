package com.bokjips.server.util.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ApiLoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=utf-8");

        List<String> data = new ArrayList<>();
        data.add("code:401");
        data.add("아이디 혹은 비밀번호를 다시 확인 해주세요.");
        JSONPObject json = new JSONPObject("Error",data);

        ObjectMapper objectMapper =new ObjectMapper();
        PrintWriter out = response.getWriter();

        out.print(objectMapper.writeValueAsString(json));
    }
}
