package ru.levelp.myapp.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter (urlPatterns = "/*")
public class RequestLogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;

        System.out.println("Reequest " + req.getRequestURI() + " from " + req.getRemoteAddr());

//        if (req.getSession(false) == null) {
//            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
        filterChain.doFilter(request, response);
    }


}
