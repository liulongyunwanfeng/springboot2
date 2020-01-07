package com.django.springboot2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * 使用springboot 为我们生成的StandarServletMultipartResovler，都不用自己配置
 *
 *
 */
@Controller
@RequestMapping("/file")
public class FileController {

    /*@RequestMapping(value = "/commonfileuplaod",method = RequestMethod.POST)
    @ResponseBody
    public String commonFileUplaod(@RequestParam("files") MultipartFile[] files) throws Exception{
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
    }*/

    /*@RequestMapping(value = "/fileuplaod",method = RequestMethod.POST)
    @ResponseBody
    public String fileuplaod(MultipartFile file) throws Exception{
        try {
            if(file!=null){
                    FileUtils.writeByteArrayToFile(new File("e:/upload/"+file.getOriginalFilename()),file.getBytes());
            }
        } catch (IOException e) {
            throw e;
        }
        return "ok";
    }*/

    // 使用HttpServletRequest作为参数
    @PostMapping("/upload/request")
    @ResponseBody
    public Map<String, Object> uploadRequest(HttpServletRequest request) {
        boolean flag = false;
        MultipartHttpServletRequest mreq = null;
        // 强制转换为MultipartHttpServletRequest接口对象
        if (request instanceof MultipartHttpServletRequest) {
            mreq = (MultipartHttpServletRequest) request;
        } else {
            return dealResultMap(false, "上传失败");
        }
        // 获取MultipartFile文件信息
        MultipartFile mf = mreq.getFile("file");
        // 获取源文件名称
        String fileName = mf.getOriginalFilename();
        File file = new File(fileName);
        try {
            // 保存文件
            mf.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }


    // 使用HttpServletRequest作为参数
    @PostMapping("/uploads/request")
    @ResponseBody
    public Map<String, Object> uploadsRequest(HttpServletRequest request) {
        boolean flag = false;
        MultipartHttpServletRequest mreq = null;
        // 强制转换为MultipartHttpServletRequest接口对象
        if (request instanceof MultipartHttpServletRequest) {
            mreq = (MultipartHttpServletRequest) request;
        } else {
            return dealResultMap(false, "上传失败");
        }

        List<MultipartFile> files = mreq.getFiles("file");

        for ( MultipartFile mf: files ) {

            // 获取源文件名称
            String fileName = mf.getOriginalFilename();
            File file = new File(fileName);
            try {
                // 保存文件
                mf.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
                return dealResultMap(false, "上传失败");
            }

        }

        return dealResultMap(true, "上传成功");
    }




    // 使用Spring MVC的MultipartFile类作为参数
    @PostMapping("/upload/multipart")
    @ResponseBody
    public Map<String, Object> uploadMultipartFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File dest = new File(fileName);
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }


    @PostMapping("/uploads/multipart")
    @ResponseBody
    public Map<String, Object> uploadsMultipartFile(@RequestParam("file") MultipartFile[] files) {

        for (MultipartFile file: files) {
            String fileName = file.getOriginalFilename();
            File dest = new File(fileName);
            try {
                file.transferTo(dest);
            } catch (Exception e) {
                e.printStackTrace();
                return dealResultMap(false, "上传失败");
            }
        }
        return dealResultMap(true, "上传成功");
    }


    /**
     * 推荐使用Part 接口进行文件上传，只依赖于servlet本身，写入也很方便
     * @param file
     * @return
     */
    @PostMapping("/upload/part")
    @ResponseBody
    public Map<String, Object> uploadPart(Part file) {
        // 获取提交文件名称
        String fileName = file.getSubmittedFileName();
        try {
            // 写入文件
            file.write(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return dealResultMap(false, "上传失败");
        }
        return dealResultMap(true, "上传成功");
    }


    /**
     * 推荐使用Part 接口进行文件上传，只依赖于servlet本身，写入也很方便
     * @param files
     * @return
     */

    @PostMapping("/uploads/part")
    @ResponseBody
    public Map<String, Object> uploadsPart(@RequestParam("file") Part[] files) {


        for (Part file:files) {
            // 获取提交文件名称
            String fileName = file.getSubmittedFileName();
            try {
                // 写入文件
                file.write(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return dealResultMap(false, "上传失败");
            }
        }

        return dealResultMap(true, "上传成功");
    }



    // 处理上传文件结果
    private Map<String, Object> dealResultMap(boolean success, String msg) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", success);
        result.put("msg", msg);
        return result;
    }



}
