package com.django.springboot2.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.django.springboot2.annotation.AopAnnotation;
import com.django.springboot2.pojo.domain.Organization;
import com.django.springboot2.service.OrganizationService;
import com.django.springboot2.service.PdfExportService;
import com.django.springboot2.web.validator.OrgnazitionValidator;
import com.django.springboot2.web.view.PdfView;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.validation.Valid;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.List;

/**
 * @author liulongyun
 * @create 2019/6/4 21:57
 **/
@RestController
@RequestMapping("/org")
public class OrganizationController extends  BaseController {

    @Autowired
    private OrganizationService organizationService = null;






//     绑定自定义validator
//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        // 绑定验证器
//        binder.setValidator(new OrgnazitionValidator());
//    }

    /**
     * 这里最好不要用ModelAndView作为参数，测试发现如果是使用InitBinder
     * 做数据验证，控制器方法里如果ModelAndView作为参数，会发现数据验证报错
     * 很奇怪
     * @return
     */

    @RequestMapping(value = "/toorgpage")
    public ModelAndView toOrgPage(){
        return new ModelAndView("org");

    }





    @PostMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity addOrg(@Valid @RequestBody Organization org,
                                  @PathVariable("id") BigDecimal id) {

        ResponseEntity<Organization> responseEntity = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        //        System.out.println(1/0); 除0异常验证
        org.setId(id);
        org.setOrgRegistDate(new Date());
        organizationService.add(org);
        httpHeaders.add("success","true");
        Map<String,Object> resultContent = new HashMap<>();
        resultContent.put("successMsg","用户添加成功");
        responseEntity =new ResponseEntity(resultContent,httpHeaders,HttpStatus.ACCEPTED);
        return  responseEntity;

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<BigDecimal> deleteOrg(@PathVariable("id") BigDecimal id)throws Exception{

        organizationService.delete(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("success","true");
        ResponseEntity<BigDecimal> responseEntity =new ResponseEntity(id,httpHeaders,HttpStatus.ACCEPTED);
        return  responseEntity;

    }


    @DeleteMapping("/{id}/{orgName}")
    public ResponseEntity<BigDecimal> updateOrg(@PathVariable("id") BigDecimal id,@PathVariable("orgName") String orgName)throws Exception{

        Organization organization = new Organization();
        organization.setId(id);
        organization.setOrgName(orgName);
        organizationService.update(organization);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("success","true");
        ResponseEntity<BigDecimal> responseEntity =new ResponseEntity(httpHeaders,HttpStatus.ACCEPTED);
        return  responseEntity;

    }


    @GetMapping("/{id}")
    public ResponseEntity<Organization> getOrgById(@PathVariable("id") BigDecimal id) throws Exception{

        Organization org = organizationService.getById(id);
        HttpHeaders httpHeaders = new HttpHeaders();

        if(org!=null && org.getId()!= null){
            httpHeaders.add("success","true");
        }else{
            httpHeaders.add("success","false");
        }
        ResponseEntity<Organization> responseEntity =new ResponseEntity(org,httpHeaders,HttpStatus.OK);
        return  responseEntity;
    }



    /**
     * 当查询参数很多时还是用post方式进行查询，在请求体中封装查询json参数，不要台纠结于只能get查询，而post用于创建的规范吧
     * @param organization
     * @return
     * @throws Exception
     */

    @PostMapping("/search")
    public ResponseEntity<Organization> queryOrg(@RequestBody Organization organization) throws Exception{

        List<Organization> orgs = organizationService.getByWhere(organization);
        HttpHeaders httpHeaders = new HttpHeaders();

        if(orgs.size()>0){
            httpHeaders.add("success","true");
        }else{
            httpHeaders.add("success","false");
        }
        ResponseEntity<Organization> responseEntity =new ResponseEntity(orgs,httpHeaders,HttpStatus.OK);
        return  responseEntity;
    }

    @GetMapping("/export/pdf/{orgName}")
    @AopAnnotation(desc = "记录导出日志")
    public ModelAndView exportPdf(@PathVariable(value = "orgName") String orgName) throws Exception{

        Organization org = new Organization();
        org.setOrgName(orgName);

        List<Organization> orgs = organizationService.getByWhere(org);
        ModelAndView mv = new ModelAndView();
        mv.addObject("orgList",orgs);
        View view = new PdfView(exportService());
        mv.setView(view);
        return  mv;

    }

    // 导出PDF自定义

    private PdfExportService exportService(){
        // 使用lambda代码块实现
        return ((model, document, writer, request, response) -> {

            try {
                // A4纸张
                document.setPageSize(PageSize.A4);
                // 标题
                document.addTitle("用户信息");
                // 换行
                document.add(new Chunk("\n"));
                // 表格，3列
                PdfPTable table = new PdfPTable(3);
                // 单元格
                PdfPCell cell = null;
                // 字体，定义为蓝色加粗
                Font f8 = new Font();
                f8.setColor(Color.BLUE);
                f8.setStyle(Font.BOLD);
                // 标题
                cell = new PdfPCell(new Paragraph("id", f8));
                // 居中对齐
                cell.setHorizontalAlignment(1);
                // 将单元格加入表格
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("orgName", f8));
                // 居中对齐
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                cell = new PdfPCell(new Paragraph("note", f8));
                cell.setHorizontalAlignment(1);
                table.addCell(cell);
                // 获取数据模型中的用户列表
                List<Organization> orgList = (List<Organization>) model.get("orgList");
                for (Organization org : orgList) {
                    document.add(new Chunk("\n"));
                    cell = new PdfPCell(new Paragraph(org.getId() + ""));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(org.getOrgName()));
                    table.addCell(cell);
                    String note = org.getNote() == null ? "" : org.getNote();
                    cell = new PdfPCell(new Paragraph(note));
                    table.addCell(cell);
                }
                // 在文档中加入表格
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }


        });
    }

    @PostMapping("/searchpage")
    public ResponseEntity<IPage<Organization>> queryOrgPage(@RequestBody Organization organization) throws Exception{

        IPage<Organization> orgPage = organizationService.getPageByWhere(organization);

        HttpHeaders httpHeaders = new HttpHeaders();

        if(orgPage.getRecords().size()>0){
            httpHeaders.add("success","true");
        }else{
            httpHeaders.add("success","false");
        }
        ResponseEntity<IPage<Organization>> responseEntity =new ResponseEntity(orgPage,httpHeaders,HttpStatus.CREATED);
        return  responseEntity;
    }

}
