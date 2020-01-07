package com.django.springboot2.service;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

/**
 * @author liulongyun
 * @create 2019/6/7 21:58
 **/
public interface PdfExportService {

    public void make(Map<String,Object> model, Document document, PdfWriter writer,
                     HttpServletRequest request, HttpServletResponse response);
}
