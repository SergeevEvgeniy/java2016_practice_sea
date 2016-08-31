package com.epam.cars.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    private final String userID = "admin";
    private final String password = "admin";

    @RequestMapping(value = "/LoginController", method = RequestMethod.POST)
    public void doPost(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {

        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        if (userID.equals(user) && password.equals(pwd)) {
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(30 * 60);
            Cookie userName = new Cookie("user", user);
            userName.setMaxAge(30 * 60);

            String sessionID = null;
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {
                        sessionID = cookie.getValue();
                    }
                }
            }

            session.setAttribute("user", user);
            session.setAttribute("sessionID", sessionID);

            resp.addCookie(userName);
            resp.sendRedirect("LoginSuccess.jsp");

        } else {
            PrintWriter out = resp.getWriter();
            out.println("<font color=red>User name or password is wrong.</font>");
            req.getRequestDispatcher("/login.html").forward(req, resp);
        }
    }
}
