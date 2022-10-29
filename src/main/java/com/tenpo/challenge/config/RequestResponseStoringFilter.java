package com.tenpo.challenge.config;

import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestResponseStoringFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        /*HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        long start = System.currentTimeMillis();
        try {
            chain.doFilter(req, res);
        } finally {
            // Measure elapsed time
            long elapsed = System.currentTimeMillis() - start;
            // store request data and store response data in a DB
            System.out.println(request.getRequestURI() + " - " + request.getMethod());
            ServletServerHttpResponse outputMessage = new ServletServerHttpResponse(response);

            System.out.println(response.getStatus() + " - " + outputMessage.getBody());

        }*/

    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig arg0) throws ServletException {}

}
