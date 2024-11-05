package com.servlet.adminServlet.course;

import com.entity.Course;
import com.service.CourseService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Createcourse", value = "/admin/createcourse")
public class Createcourse extends HttpServlet {
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
        String Description = request.getParameter("describe");
        int Batch = Integer.parseInt(request.getParameter("batch"));
        int Mustchose = Integer.parseInt(request.getParameter("mustchose"));
        Course course = new Course();
        course.setName(Name);
        course.setDescription(Description);
        course.setBatch(Batch);
        course.setMustchose(Mustchose);
        response.getWriter().write(CourseService.AddCourse(course));
    }
}
