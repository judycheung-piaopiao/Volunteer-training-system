package com.servlet.adminServlet.classes;

import com.entity.Class;
import com.service.ClassService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddClass", value = "/admin/addclass")
public class AddClass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String Name = request.getParameter("name");
        String time = request.getParameter("time");
        String Location = request.getParameter("location");
        String TeacherNumber = request.getParameter("teacherno");
        String TeacherName = request.getParameter("teachername");
        int MaxPeople = Integer.parseInt(request.getParameter("maxpeople"));
        int Course = Integer.parseInt(request.getParameter("course"));
        Class classes = new Class();
        classes.setName(Name);
        classes.setTime(time);
        classes.setLocation(Location);
        classes.setTeacherNo(TeacherNumber);
        classes.setTeacherName(TeacherName);
        classes.setMaxPeople(MaxPeople);
        classes.setCourse(Course);
        response.getWriter().write(ClassService.AddClass(classes));
    }
}
