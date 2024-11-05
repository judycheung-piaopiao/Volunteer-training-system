package com.util;

import com.entity.Student;
import com.entity.Teacher;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ImportExcel {
    public static List<Student> analyExcelUserBlockS(HttpServletRequest request, String fileName){
        List<Student> list = new ArrayList<Student>();
        String targetDirectory = request.getServletContext().getRealPath("");
        File target = new File(targetDirectory,fileName);
        try {
            FileInputStream fi = new FileInputStream(target);
            Workbook workbook = WorkbookFactory.create(fi);
            //获取第一张sheet表
            Sheet sheet = workbook.getSheetAt(0);
            //获取总行数
            int rowCount = sheet.getPhysicalNumberOfRows();
            //遍历每一行
            for(int r = 0; r < rowCount ; r++){
                Student student = new Student();
                Row row = sheet.getRow(r);
                //用户id
                Cell Number = row.getCell(0);
                if(Number != null){
                    //id.setCellType(Cell.CELL_TYPE_STRING);//deprecated
                    Number.setCellType(CellType.STRING);
                    student.setNumber(Number.getStringCellValue());
                }else{
                    student.setNumber(" ");
                }
                //用户id
                Cell Name = row.getCell(1);
                if(Name != null){
                    //id.setCellType(Cell.CELL_TYPE_STRING);//deprecated
                    Name.setCellType(CellType.STRING);
                    student.setName(Name.getStringCellValue());
                }else{
                    student.setName("");
                }
                //用户年龄
                Cell Email = row.getCell(2);
                if(Email != null){
                    Email.setCellType(CellType.STRING);
                    student.setEmail(Email.getStringCellValue());
                }else{
                    student.setSex("");
                }
                //用户学院
                Cell Acdemy = row.getCell(3);
                if(Acdemy != null){
                    Acdemy.setCellType(CellType.STRING);
                    student.setAcademy(Acdemy.getStringCellValue());
                }else{
                    student.setAcademy("");
                }

                Cell Other = row.getCell(4);
                if(Other != null){
                    Other.setCellType(CellType.STRING);
                    student.setOther(Other.getStringCellValue());
                }else{
                    student.setOther("");
                }
                list.add(student);
                student = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static List<Teacher> analyExcelUserBlockT(HttpServletRequest request, String fileName){
        List<Teacher> list = new ArrayList<Teacher>();
        String targetDirectory = request.getServletContext().getRealPath("");
        File target = new File(targetDirectory,fileName);
        try {
            FileInputStream fi = new FileInputStream(target);
            Workbook workbook = WorkbookFactory.create(fi);
            //获取第一张sheet表
            Sheet sheet = workbook.getSheetAt(0);
            //获取总行数
            int rowCount = sheet.getPhysicalNumberOfRows();
            //遍历每一行
            for(int r = 0; r < rowCount ; r++){
                Teacher teacher = new Teacher();
                Row row = sheet.getRow(r);
                //用户name
                Cell name = row.getCell(0);
                if(name != null){
                    //id.setCellType(Cell.CELL_TYPE_STRING);//deprecated
                    name.setCellType(CellType.STRING);
                    teacher.setName(name.getStringCellValue());
                }else{
                    teacher.setName("");
                }
                //用户id
                Cell id = row.getCell(1);
                if(id != null){
                    //id.setCellType(Cell.CELL_TYPE_STRING);//deprecated
                    id.setCellType(CellType.STRING);
                    teacher.setNumber(id.getStringCellValue());
                }else{
                    teacher.setNumber("");
                }
                list.add(teacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}