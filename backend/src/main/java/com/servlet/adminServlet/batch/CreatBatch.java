package com.servlet.adminServlet.batch;

import com.entity.Batch;
import com.service.BatchService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreateBatch", value = "/admin/createbatch")
public class CreatBatch extends HttpServlet {
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
        String Describe = request.getParameter("describe");
        Batch batch = new Batch();
        batch.setName(Name);
        batch.setDescribe(Describe);
        response.getWriter().write(BatchService.CreateBatch(batch));
    }
}
