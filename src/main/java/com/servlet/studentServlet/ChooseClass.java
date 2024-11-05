package com.servlet.studentServlet;

import com.service.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChooseClass", value = "/student/chooseclass")
public class ChooseClass extends HttpServlet {
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
        String name = request.getParameter("name");
        String number = request.getParameter("number");
        int classes = Integer.parseInt(request.getParameter("classes"));
        int course = Integer.parseInt(request.getParameter("course"));
        response.getWriter().write(StudentService.chooseClass(name,number,classes,course));
    }
}
//http://league.eksys.hdu.edu.cn/student/chooseclass?name=%E6%9D%8E%E5%9B%9B&number=ZYZ6666666&course=7&classes=13