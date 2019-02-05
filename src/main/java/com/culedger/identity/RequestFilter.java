package com.culedger.identity;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*")
@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String mdcData = String.format("[requestId:%s] ", requestId());
            MDC.put("mdcData", mdcData); //Referenced from logging configuration.
            System.out.println("Remote Host:"+request.getRemoteHost());
            System.out.println("Remote Address:"+request.getRemoteAddr());
            chain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }

    private String requestId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
