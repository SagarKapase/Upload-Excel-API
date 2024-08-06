package com.exceldata.controller;

import com.exceldata.entities.Student;
import com.exceldata.helper.ExcelHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @PostMapping("/upload-csv")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){
        if (ExcelHelper.checkExcelFormat(file)){
            //this.studentService.save(file);
            List<Map<String,Object>> rowsData = new ArrayList<>();

            try(InputStream is = file.getInputStream(); Workbook workbook = new XSSFWorkbook(is))
            {
                Sheet sheet = workbook.getSheetAt(0);
                int rowCount = 0;

                for (Row row: sheet)
                {
                    if (rowCount >= 5)
                    {
                        break;
                    }
                    Map<String, Object> rowData = new HashMap<>();
                    for (Cell cell: row)
                    {
                        switch (cell.getCellType())
                        {
                            case STRING :
                                rowData.put("Column"+cell.getColumnIndex(),cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell))
                                {
                                    rowData.put("Column"+cell.getColumnIndex(),cell.getDateCellValue().toString());
                                }else
                                {
                                    rowData.put("Column"+cell.getColumnIndex(),cell.getNumericCellValue());
                                }
                                break;
                            case BOOLEAN:
                                rowData.put("Column" + cell.getColumnIndex(), cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                rowData.put("Column" + cell.getColumnIndex(), cell.getCellFormula());
                                break;
                            default:
                                rowData.put("Column" + cell.getColumnIndex(), "Unsupported Cell Type");
                                break;
                        }
                    }
                    rowsData.add(rowData);
                    rowCount++;
                }
            }catch (Exception ex)
            {
                logger.error("Error processing file", ex);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file");
            }
            return ResponseEntity.ok(rowsData);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please do upload EXCEL file");
    }
}
