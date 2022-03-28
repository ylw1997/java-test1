package com.yangliwei.test1.controller;

import com.yangliwei.test1.common.AjaxResult;
import com.yangliwei.test1.common.Upload;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 *  公共控制器
 * @author yangliwei
 */
@RestController
public class CommonController {

    @GetMapping("/")
    @PreAuthorize("hasAuthority('home111')")
   public AjaxResult homePage (){
       return AjaxResult.success("欢迎使用");
   }

    /**
     *  上传文件
     * @param file 文件
     * @return AjaxResult
     */
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file) {
        try {
            String url = Upload.uploadFile(file);
            return AjaxResult.success(url);
        } catch (Exception e) {
            return AjaxResult.error("上传失败"+ e.getMessage());
        }
    }

    /**
     * 文件下载
     */
    @Data
    static class FileObj {
        private String url;
        private String name;;
        private String type;
        private long size;
    }

    @PostMapping("/upload/list")
    public AjaxResult uploadFiles(List<MultipartFile> files) {
        try {
            List<FileObj> fileObjs = new ArrayList<>();
            for (MultipartFile file : files) {
                String url = Upload.uploadFile(file);
                FileObj fileObj = new FileObj();
                fileObj.setUrl(url);
                fileObj.setName(file.getOriginalFilename());
                fileObj.setType(file.getContentType());
                fileObj.setSize(file.getSize());
                fileObjs.add(fileObj);
            }
            return AjaxResult.success(fileObjs);
        } catch (Exception e) {
            return AjaxResult.error("上传失败"+ e.getMessage());
        }
    }
}
