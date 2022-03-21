package com.yangliwei.test1.controller;

import com.yangliwei.test1.common.AjaxResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 *  公共控制器
 * @author yangliwei
 */
@RestController
public class CommonController {

    @Value("${spring.web.upload-path}")
    private String uploadPath;

    /**
     *  上传文件
     * @param file 文件
     * @param request 请求
     * @return AjaxResult
     */
    @PostMapping("/upload")
    public AjaxResult upload(MultipartFile file, HttpServletRequest request) {
        try {
            File folder = new File(uploadPath);
            if (!folder.exists()) {
                if(!folder.mkdirs()){
                    return AjaxResult.error("文件夹创建失败");
                }
            }
            String oldName = file.getOriginalFilename() == null ? "" : file.getOriginalFilename();
            String newName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
            file.transferTo(new File(folder, newName));
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + newName;
            return AjaxResult.success(filePath);
        }
        catch (Exception e) {
            return AjaxResult.error("上传失败:${}", e.getMessage());
        }
    }
}
