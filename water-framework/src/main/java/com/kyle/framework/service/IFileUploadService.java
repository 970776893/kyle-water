package com.kyle.framework.service;

import com.kyle.framework.model.ModelResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhangkai on 3/30/16.
 */
public interface IFileUploadService {
    ModelResult<String> upload(MultipartFile file);
}
