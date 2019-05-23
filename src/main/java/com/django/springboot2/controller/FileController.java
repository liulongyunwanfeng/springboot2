package com.django.springboot2.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName FileController
 * @Author Administrator
 * @Date 2019/5/8
 * @Version 1.0
 * @Description TODO
 *
 * SpringMvc用MultipartFile做文件上传，前端上传的文件字段的key要和接口接收文件的参数名称一致
 * 多文件上传的时候需要加@RequestParam("files")注解
 *
 *
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping(value = "/commonfileuplaod",method = RequestMethod.POST)
    public @ResponseBody
    String commonFileUplaod(@RequestParam("files") MultipartFile[] files) throws Exception{
        try {
            if(files!=null&&files.length>0){
                for (int i = 0; i < files.length; i++) {
                    FileUtils.writeByteArrayToFile(new File("e:/upload/"+files[i].getOriginalFilename()),files[i].getBytes());
                }
            }
        } catch (IOException e) {
            throw e;
        }
        return "ok";
    }

    @RequestMapping(value = "/fileuplaod",method = RequestMethod.POST)
    public @ResponseBody
    String fileuplaod(MultipartFile file) throws Exception{
        try {
            if(file!=null){
                    FileUtils.writeByteArrayToFile(new File("e:/upload/"+file.getOriginalFilename()),file.getBytes());
            }
        } catch (IOException e) {
            throw e;
        }
        return "ok";
    }
}
