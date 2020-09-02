package com.lot.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * 文件上传工具类
 **/
public class FileUpload {

    /**
     * 文件上传处理
     *
     * @param file
     * @return
     */
    public static String writeUploadFile(MultipartFile file, String module) {
        String filename = file.getOriginalFilename();
        // 获取当前用户程序所在目录
        String userDir = System.getProperties().getProperty("user.dir");
        String realpath = userDir + "/" + module + "/img/";
        File fileDir = new File(realpath);
        if (!fileDir.exists())
            fileDir.mkdirs();
        String extname = FilenameUtils.getExtension(filename);
        String allowImgFormat = "gif,jpg,jpeg,png";
        if (!allowImgFormat.contains(extname.toLowerCase())) {
            return "NOT_IMAGE";
        }
        extname = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if(extname.equals("blob")){
            filename = Math.abs(file.getOriginalFilename().hashCode()) + RandomUtils.createRandomString(8) + ".jpg";
        }else {
            filename = Math.abs(file.getOriginalFilename().hashCode()) + RandomUtils.createRandomString(8) + "." + extname;
        }
        InputStream input = null;
        FileOutputStream fos = null;
        try {
            input = file.getInputStream();
            fos = new FileOutputStream(realpath + "/" + filename);
            IOUtils.copy(input, fos);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(fos);
        }

        return "lot/img/" + filename;
    }
}
