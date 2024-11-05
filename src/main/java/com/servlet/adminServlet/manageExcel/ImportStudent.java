package com.servlet.adminServlet.manageExcel;

import com.entity.Student;
import com.service.StudentService;
import com.util.ImportExcel;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@MultipartConfig
// 标识Servlet支持文件上传
@WebServlet(value = "/admin/importstudent")
public class ImportStudent extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        int bench = Integer.parseInt(request.getParameter("bench"));
        String savePath = request.getServletContext().getRealPath("");
        Collection<Part> parts = request.getParts();
        Part part = request.getPart("file");
        String header = part.getHeader("content-disposition");
        String fileName = getFileName(header);
        part.write(savePath + File.separator + fileName);
        List<Student> list = ImportExcel.analyExcelUserBlockS(request, fileName);
        JSONArray jsonArray = new JSONArray();
        for (Student u : list) {
             jsonArray.add(StudentService.importStudent(u,bench));
        }
        response.getWriter().write(jsonArray.toString());
    }

    public String getFileName(String header) {
        String[] tempArr1 = header.split(";");
        String[] tempArr2 = tempArr1[2].split("=");
        String fileName = tempArr2[1].substring(
                tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
        String result = "";
        if(fileName.lastIndexOf(".")!= -1){
            String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            result = UUID.randomUUID().toString()+fileType;
        }
        return result;
    }
}