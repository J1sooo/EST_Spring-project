package com.estsoft.demo.filter;

// RequestURI 정보 로그 남기기

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SecondFilter implements Filter {
    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws ServletException {
        log.info("SecondFilter init()");
    }

    @Override
    public void destroy() {
        log.info("SecondFilter destroy()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String queryString = request.getQueryString();

        log.info("SecondFilter - requestURI: {}", queryString);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
