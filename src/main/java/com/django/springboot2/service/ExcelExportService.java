package com.django.springboot2.service;
import org.apache.poi.ss.usermodel.Workbook;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author liulongyun
 * @create 2019/6/8 13:02
 **/
public interface ExcelExportService {

    public void make(Map<String, Object> model, Workbook workbook,
                     HttpServletRequest request,
                     HttpServletResponse response);

}
