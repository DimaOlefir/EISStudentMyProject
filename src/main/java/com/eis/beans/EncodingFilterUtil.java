package com.eis.beans;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilterUtil implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/xhtml+xml");
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}
