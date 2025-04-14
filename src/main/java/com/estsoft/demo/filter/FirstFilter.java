package com.estsoft.demo.filter;

// RequestURI 정보 로그 남기기

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class FirstFilter implements Filter {
//    private final static Logger log = Logger.getLogger(FirstFilter.class.getName());

    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws ServletException {
        log.info("FirstFilter init()");
    }

    @Override
    public void destroy() {
        log.info("FirstFilter destroy()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();

        log.info("requestURI: {}", requestURI);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
