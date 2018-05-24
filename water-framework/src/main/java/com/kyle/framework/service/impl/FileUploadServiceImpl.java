package com.kyle.framework.service.impl;

import com.kyle.framework.model.ModelResult;
import com.kyle.framework.model.ReturnCodeEnum;
import com.kyle.framework.service.IFileUploadService;
import com.kyle.framework.utils.DateUtils;
import com.kyle.framework.utils.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhangkai on 3/30/16.
 */
//@Service
public class FileUploadServiceImpl implements IFileUploadService {
    @Value("${upload.img.address.save}")
    private String imgSaveAddress;

    @Value("${upload.img.address.visit}")
    private String imgVisitAddress;

    Log logger =  LogFactory.getLog(FileUploadServiceImpl.class);

    @Override
    public ModelResult<String> upload(MultipartFile file) {

        ModelResult<String> result = new ModelResult<>("上传成功");

        String fileName = DateUtils.format(System.currentTimeMillis(), "yyyy-MM-dd_HH-mm-ss_");
        fileName += RandomUtils.random(4);
        //后缀名
        fileName += file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        FileOutputStream outputStream = null ;
        try {
            outputStream = new FileOutputStream(this.imgSaveAddress + "/" + fileName);
            outputStream.write(file.getBytes());
        }catch (IOException e){
            logger.error("上传失败" + e);
            result.setCode(ReturnCodeEnum.REQUEST_FAILED.code);
            result.setMessage("上传失败");
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("upload file success : " + file.getName() + "->" + fileName);
        result.setData(this.imgVisitAddress + "/" + fileName);
        return result;
    }
}
