package com.zxhan.modular.controller;

import com.zxhan.core.constant.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    PathUtil pathUtil;
    String uploadDir ;

    //单个文件，多个文件均可
    @RequestMapping("/multi")
    public String multiFile(MultipartFile[] file,String id,String name) throws IOException {
        uploadDir = pathUtil.getUploadDir();
        //multipart/form-data，file不为null,大小为0。
        //x-www-form-urlencoded,file为null
        if(file!=null){
            for (MultipartFile multipartFile : file) {
                multipartFile.transferTo(new File(uploadDir,multipartFile.getOriginalFilename()));
            }
        }
        return "200";
    }

    //request多文件
    @RequestMapping("/request")
    public String requestFile(HttpServletRequest request) throws IOException {
        uploadDir = pathUtil.getUploadDir();
        if (!(request instanceof MultipartHttpServletRequest)) {
            String errorMsg = "your post form is not support ENCTYPE='multipart/form-data' ";
            System.err.println(errorMsg);
            throw new RuntimeException(errorMsg);
        }

        //当请求为，x-www-form-urlencoded格式时，会出现类转换异常
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = multipartHttpServletRequest.getFiles("file");
        for (MultipartFile multipartFile : fileList) {
            multipartFile.transferTo(new File(uploadDir,multipartFile.getOriginalFilename()));
        }
        return "success";
    }



}
