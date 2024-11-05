package com.servlet.adminServlet.manageExcel;

import com.entity.Student;
import com.entity.Teacher;
import com.service.StudentService;
import com.service.TeacherService;
import com.util.ImportExcel;
import net.sf.json.JSONArray;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@MultipartConfig
@WebServlet(name = "ImportTeacher", value = "/admin/importteacher")
public class ImportTeacher extends HttpServlet {
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String savePath = request.getServletContext().getRealPath("");
        Collection<Part> parts = request.getParts();
        Part part = request.getPart("file");
        String header = part.getHeader("content-disposition");
        String fileName = getFileName(header);
        part.write(savePath + File.separator + fileName);
        List<Teacher> list = ImportExcel.analyExcelUserBlockT(request, fileName);
        System.out.println(list);
        JSONArray jsonArray = new JSONArray();
        for (Teacher t : list) {
            jsonArray.add(TeacherService.importTeacher(t));
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
