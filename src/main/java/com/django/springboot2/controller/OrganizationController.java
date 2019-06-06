package com.django.springboot2.controller;

import com.django.springboot2.pojo.domain.Organization;
import com.django.springboot2.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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


    @PostMapping("/{id}")
    public  ResponseEntity addOrg(@RequestBody Organization org,@PathVariable("id") BigDecimal id) throws Exception{
        org.setId(id);
        organizationService.add(org);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("success","true");
        ResponseEntity<Organization> responseEntity =new ResponseEntity(httpHeaders,HttpStatus.ACCEPTED);
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
        ResponseEntity<Organization> responseEntity =new ResponseEntity(orgs,httpHeaders,HttpStatus.CREATED);
        return  responseEntity;
    }



}
