package com.zhangkai.gencode;

import lombok.Data;

/**
 * Created by zhangkai on 15-6-9.
 */
@Data
public class DataStruct {
    private String fieldDb;
    private String typeDb;
    private String comment;

    private String fieldJdbc;
    private String typeJdbc;


    private String typeJava;
    private String fieldJava;
    private String fieldJavaMethod;
}
