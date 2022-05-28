package com.bokjips.server.util.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {
    private AntPathMatcher antPathMatcher;
    private String pattern;

    public ApiCheckFilter(String pattern) {
        this.antPathMatcher = new AntPathMatcher();
        this.pattern = pattern;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       log.info("REQUEST URI: "+request.getRequestURI());

       if(antPathMatcher.match(pattern,request.getRequestURI())) {
           boolean checkHeader = checkAuthHeader(request);
           if(checkHeader) {
               filterChain.doFilter(request, response);
           }else {
               response.setStatus(HttpServletResponse.SC_FORBIDDEN);
               response.setContentType("application/json;charset=utf-8");

               List<String> data = new ArrayList<>();
               data.add("code:403");
               data.add("FAIL CHECK API TOKEN");
               JSONPObject json = new JSONPObject("Error",data);

               PrintWriter out = response.getWriter();
               ObjectMapper objectMapper = new ObjectMapper();

               out.print(objectMapper.writeValueAsString(json));
               return;
           }
       }

        filterChain.doFilter(request, response);

    }

    private boolean checkAuthHeader(HttpServletRequest request) {
        boolean checkResult = false;
        String authHeader = request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader)){
            log.info("Authorization exist: " + authHeader);
            if(authHeader.equals("123")) {
                checkResult = true;
            }
        }

        return checkResult;
    }
}
