package com.shi.filter;

import com.shi.Utiles.Constant;
import com.shi.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        User user = (User) req.getSession().getAttribute(Constant.USER_SESSION);
        if(user == null){
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }else{
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
