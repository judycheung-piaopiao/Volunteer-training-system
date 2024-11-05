package com.servlet.adminServlet.course;

import com.entity.Course;
import com.service.CourseService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateCourse", value = "/admin/updatecourse")
public class UpdateCourse extends HttpServlet {
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
        int No = Integer.parseInt(request.getParameter("courseid"));
        String Name = request.getParameter("name");
        int Mustchose = Integer.parseInt(request.getParameter("mustchose"));
        String Description = request.getParameter("describe");
        Course course = new Course();
        course.setNo(No);
        course.setName(Name);
        course.setMustchose(Mustchose);
        course.setDescription(Description);
        response.getWriter().write(CourseService.UpdateCourse(course));
    }
}
