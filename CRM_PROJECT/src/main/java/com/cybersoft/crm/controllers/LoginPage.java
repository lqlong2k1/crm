package com.cybersoft.crm.controllers;

import com.cybersoft.crm.services.LoginService;
import com.cybersoft.crm.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "loginPage", urlPatterns = {"/login"})
public class LoginPage extends HttpServlet {

    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);

//        Khai báo một cookie tên là email
//        Cookie cookie = new Cookie("email",email);
//        cookie.setMaxAge(5 * 60);
//        resp.addCookie(cookie);
//
//        Cookie cookie1 = new Cookie("password",password);
//        cookie1.setMaxAge(5 * 60);
//        resp.addCookie(cookie1);

//        Cookie[] cookies = req.getCookies();
//        for (Cookie cookie : cookies) {
//            System.out.println("Name cookie " + cookie.getName() + " - Value " + cookie.getValue());
//        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email.isEmpty() || password.isEmpty()) {
            req.setAttribute("MSG", "Vui lòng điền đầy đủ thông tin đăng nhập");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            if (loginService.checkValidEmail(email) == false) {
                req.setAttribute("MSG", "Email vừa nhập không hợp lệ. Vui lòng thử lại...");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                boolean isLogin = loginService.checkLogin(email, password);

                if (isLogin) {
                    HttpSession session = req.getSession();
                    session.setAttribute("isLogin", true);
                    session.setMaxInactiveInterval(8 * 60);
                    resp.sendRedirect(req.getContextPath() + "/profile");
                } else {
                    req.setAttribute("MSG", "Email hoặc mật khẩu không đúng. Vui lòng thử lại...");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
                System.out.println("Kiem tra trang thai login: " + isLogin);
            }
        }
    }
}
