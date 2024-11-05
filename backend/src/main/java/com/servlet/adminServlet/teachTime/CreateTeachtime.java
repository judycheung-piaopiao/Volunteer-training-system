package com.servlet.adminServlet.teachTime;

import com.entity.TeachTime;
import com.service.TeachtimeService;
import com.util.GetSignCode;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "CreateTeachtime", value = "/admin/createteachtime")
public class CreateTeachtime extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        int classes = Integer.parseInt(request.getParameter("classes"));
        String time = request.getParameter("time");
        String location = request.getParameter("Location");
        int signcode = GetSignCode.getSignCode();
        TeachTime teachTime = new TeachTime();
        teachTime.setTime(time);
        teachTime.setLocation(location);
        teachTime.setClasses(classes);
        teachTime.setSigncode(signcode);
        response.getWriter().write(TeachtimeService.addTeachtime(teachTime));
    }
}
