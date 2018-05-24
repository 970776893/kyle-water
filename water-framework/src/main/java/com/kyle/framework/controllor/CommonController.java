package com.kyle.framework.controllor;

import com.kyle.framework.model.ModelResult;
import com.kyle.framework.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhangkai on 3/24/16.
 */

@Controller
@RequestMapping("/")
public class CommonController {

    @Autowired
    private IFileUploadService fileUploadService;

    @ResponseBody
    @RequestMapping(value = "upload/img", method = {RequestMethod.POST})
    public ModelResult<String> upload(@RequestBody MultipartFile file) {
        return fileUploadService.upload(file);
    }

}
