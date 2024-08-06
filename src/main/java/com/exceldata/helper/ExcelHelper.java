package com.exceldata.helper;

import com.exceldata.entities.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    //check that file is excel file or not
    public static boolean checkExcelFormat(MultipartFile file){

        String contentType=file.getContentType();
        String fileName = file.getOriginalFilename();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
            return true;
        }// Check file extension if MIME type is not conclusive
        else if (fileName != null && (fileName.endsWith(".xlsx") || fileName.endsWith(".xls") || fileName.endsWith(".csv"))) {
            return true;
        }else {
            return false;
        }
    }

    //convert excel to list of students
    public static List<Student> convertExcelToListOfStudents(InputStream is){
        List<Student> list=new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("data");

            int rowNumber=0;
            Iterator<Row> iterator=sheet.iterator();

            while (iterator.hasNext()){

                Row row = iterator.next();

                if (rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid=0;
                Student student=new Student();
                while (cells.hasNext()){

                    Cell cell = cells.next();

                    switch (cid)
                    {
                        case 0:
                            student.setStuId((int)cell.getNumericCellValue());
                            break;
                        case 1:
                            student.setFirstName(cell.getStringCellValue());
                            break;
                        case 2:
                            student.setLastName(cell.getStringCellValue());
                            break;
                        case 3:
                            student.setCollege(cell.getStringCellValue());
                            break;
                        case 4:
                            student.setPlacementStatus(cell.getStringCellValue());
                            break;
                        case 5:
                            student.setCompanyName(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(student);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
