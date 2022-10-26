package com.cybersoft.crm.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Kiem tra filter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        if (session.getAttribute("isLogin") != null && !session.getAttribute("isLogin").equals("")) {
            //Đã login
            boolean isLogin = (boolean) session.getAttribute("isLogin");
            if (isLogin) {
                if (request.getServletPath().equals("/login")) {
                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
                    filterChain.doFilter(request, response);
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            //Chưa login
            if (request.getServletPath().equals("/login")) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }
}
