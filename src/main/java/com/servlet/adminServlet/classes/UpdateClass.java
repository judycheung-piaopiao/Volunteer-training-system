package com.servlet.adminServlet.classes;

import com.entity.Class;
import com.service.ClassService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateClass", value = "/admin/updateclass")
public class UpdateClass extends HttpServlet {
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
        int classid = Integer.parseInt(request.getParameter("classid"));
        String name = request.getParameter("name");
        String location = request.getParameter("location");
        String teachername  = request.getParameter("teachername");
        String teacherno = request.getParameter("teacherno");
        int maxpeople = Integer.parseInt(request.getParameter("maxpeople"));
        String time = request.getParameter("time");
        Class classes = new Class();
        classes.setNo(classid);
        classes.setName(name);
        classes.setLocation(location);
        classes.setTeacherNo(teacherno);
        classes.setTeacherName(teachername);
        classes.setMaxPeople(maxpeople);
        classes.setTime(time);
        response.getWriter().write(ClassService.updateClass(classes));
    }
}
