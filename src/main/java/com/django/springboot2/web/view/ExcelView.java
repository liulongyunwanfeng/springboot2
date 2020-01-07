package com.django.springboot2.web.view;

import com.django.springboot2.service.ExcelExportService;
import org.springframework.web.servlet.view.document.AbstractXlsxStreamingView;
import org.apache.poi.ss.usermodel.Workbook;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author liulongyun
 * @create 2019/6/8 12:57
 *
 * 参考pdfview 一样用
 **/
public class ExcelView extends AbstractXlsxStreamingView {

    private ExcelExportService excelExportService = null;

    public ExcelView(ExcelExportService excelExportService) {
        this.excelExportService = excelExportService;
    }

    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {

        this.excelExportService.make(model,workbook,request,response);

    }
}
