package com.shi.servlet.user;

import com.shi.Utiles.Constant;
import com.shi.pojo.User;
import com.shi.service.user.userServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loginServlet extends HttpServlet {
    // 控制层调用业务层代码
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new userServiceImpl().login(username, password);
        if(user != null){
            req.getSession().setAttribute(Constant.USER_SESSION, user);
            resp.sendRedirect(req.getContextPath() + "/jsp/frame.jsp");
        }else{
            req.setAttribute("error", "用户名或密码不正确！");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
