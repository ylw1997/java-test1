package com.yangliwei.test1.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author ylw12
 */
@Service
public class Upload {

    /**
     * 上传文件
     * @param file 文件
     * @return 文件名
     * @throws Exception 异常
     */
    public static String uploadFile(MultipartFile file) throws Exception {
        String uploadPath  = PropertiesUtil.getProperty("${spring.web.upload-path}");
        File folder = new File(uploadPath);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new Exception("创建文件夹失败");
            }
        }
        String oldName = file.getOriginalFilename() == null ? "" : file.getOriginalFilename();
        String newName = UUID.randomUUID() + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File(folder, newName));
        return newName;
    }
}
