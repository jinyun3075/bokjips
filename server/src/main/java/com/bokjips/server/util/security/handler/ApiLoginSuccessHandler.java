package com.bokjips.server.util.security.handler;

import com.bokjips.server.domain.user.dto.UserResponseDto;
import com.bokjips.server.util.module.JwtUtil;
import com.bokjips.server.util.security.dto.BokjipsUserAuthDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class ApiLoginSuccessHandler implements AuthenticationSuccessHandler {

    private JwtUtil jwtUtil = new JwtUtil();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        BokjipsUserAuthDto requestDto = (BokjipsUserAuthDto) authentication.getPrincipal();
        try {
            UserResponseDto req = new UserResponseDto();
                    req.setUser_id(requestDto.getUser_id());
                    req.setName(requestDto.getName());
                    req.setEmail(requestDto.getEmail());
                    req.setToken(jwtUtil.generateToken("bokjips"));
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(req));

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
