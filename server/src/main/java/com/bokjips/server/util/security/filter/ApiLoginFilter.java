package com.bokjips.server.util.security.filter;

import com.bokjips.server.util.security.dto.BokjipsUserLoginDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {
    public ApiLoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        BokjipsUserLoginDto body = new ObjectMapper().readValue(request.getInputStream(),BokjipsUserLoginDto.class);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(body.getEmail(),body.getPassword());

        return getAuthenticationManager().authenticate(authToken);
    }
}
