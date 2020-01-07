package com.django.springboot2.web.view;


import com.django.springboot2.service.PdfExportService;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @author liulongyun
 * @create 2019/6/7 21:23
 **/
public class PdfView extends AbstractPdfView  {

    private PdfExportService pdfExportService = null;

    public PdfView(PdfExportService pdfExportService) {
        this.pdfExportService = pdfExportService;
    }

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
                                    PdfWriter writer, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        // 调用导出服务接口
        this.pdfExportService.make(model,document,writer,request,response);

    }
}
