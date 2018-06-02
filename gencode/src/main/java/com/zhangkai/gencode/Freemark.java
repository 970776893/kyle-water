package com.zhangkai.gencode;


import com.sun.javafx.runtime.SystemProperties;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Created by zhangkai on 15-6-9.
 */
public class Freemark {

    private static Map<String, String> dbType2JavaType = new HashMap();
    private static Map<String, String> dbType2JdbcType = new HashMap();

    private static ArrayList<DataStruct> tableData = new ArrayList();

    private static String[] tableNameList;
    private static String basePackage;
    private static String modulesPrefix;
    private static String driverName;
    private static String url;
    private static String username;
    private static String password;

    private static String classesPath = Freemark.class.getResource("/").getPath();
    private static String basePath = System.getProperty("user.dir");

    public static void main(String[] args) throws Exception {
        init();
        for (String tableName : tableNameList) {
            initTableFiled(tableName);
            createFiles(tableName);
        }
    }

    public static void createFiles(String tableName) throws Exception {
        //模板信息
        Configuration config = new Configuration();
        config.setDirectoryForTemplateLoading(new File(classesPath + "/template"));
        config.setSetting("defaultEncoding", "UTF-8");
        Map<String, Object> datas = new HashMap<String, Object>();
        datas.put("fieldList", tableData);
        datas.put("basePackage", basePackage);
        String resourceName = getJavaField(tableName);
        datas.put("resourceName", resourceName);
        String modelName = (char) (tableName.charAt(0) - 'a' + 'A') + getJavaField(tableName).substring(1);
        datas.put("modelName", modelName);
        datas.put("tableName", tableName);


        //生成Entity
        String filePath = getFilePath(1, resourceName, modelName);
        createFile(datas, config, "entity.java.ftl", filePath);


        //生成mybatisXML
        filePath = getFilePath(2, resourceName, modelName);
        createFile(datas, config, "mybatisMapper.xml.ftl", filePath);

        //生成IDAO
        filePath = getFilePath(3, resourceName, modelName);
        createFile(datas, config, "idao.java.ftl", filePath);

        //生成IService
        filePath = getFilePath(4, resourceName, modelName);
        createFile(datas, config, "iservice.java.ftl", filePath);

        //生成ServiceImpl
        filePath = getFilePath(5, resourceName, modelName);
        createFile(datas, config, "serviceimpl.java.ftl", filePath);

        //生成controller
        filePath = getFilePath(6, resourceName, modelName);
        createFile(datas, config, "controller.java.ftl", filePath);

    }

    //生成文件
    private static void createFile(Map<String, Object> datas, Configuration config, String templateName, String pathPath) throws Exception {
        //文件夹是否存在
        String dir = pathPath.substring(0, pathPath.lastIndexOf("/"));
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        File outFile = new File(pathPath);
        if(outFile.exists()) {
            Scanner scan = new Scanner(System.in);
            System.out.print("文件(" + outFile.getAbsolutePath() + ")已存在.\n请输入命令：1-覆盖，2-取消 :");
           do{
               String command = scan.nextLine();
               if("1".equals(command)){
                   System.out.println("忽略文件:" + outFile.getAbsolutePath());
                   return;
               }else if("2".equals(command)){
                   break;
               }else{
                   System.out.print("无效命令：请输入命令：1-覆盖，2-取消 :");
               }
           }while(true);
        }
        Template temp = config.getTemplate(templateName);
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
        temp.process(datas, out);
        out.close();
        System.out.println("创建成功:" + pathPath);
    }


    private static void initTableFiled(String tableName) throws SQLException {
        String sql = "show full columns from " + tableName;
        Connection con = DriverManager.getConnection(url, username, password);
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            String fieldName = result.getString("field").toLowerCase();
            String type = result.getString("type").split("\\(")[0];
            String comment = result.getString("comment");
            DataStruct temp = new DataStruct();

            temp.setFieldDb(fieldName);
            temp.setTypeDb(type);
            temp.setComment(comment);

            String javaField = getJavaField(fieldName);
            temp.setFieldJava(javaField);
            if (fieldName.startsWith("is") && type.equals("tinyint")) {
                type = "istinyint";
            }
            temp.setTypeJava(dbType2JavaType.get(type));
            temp.setFieldJavaMethod((char) (javaField.charAt(0) - 'a' + 'A') + javaField.substring(1));

            temp.setFieldJdbc(fieldName);
            temp.setTypeJdbc(dbType2JdbcType.get(type));

            tableData.add(temp);
        }
    }

    /**
     * 转换成驼峰式的明明方式
     *
     * @param field
     */
    public static String getJavaField(String field) {
        StringBuilder fileTemp = new StringBuilder();
        boolean isUpper = false;
        for (int i = 0; i < field.length(); i++) {
            if (field.charAt(i) == '_') {
                isUpper = true;
                continue;
            }
            if (isUpper) {
                fileTemp.append((char) (field.charAt(i) - 'a' + 'A'));
                isUpper = false;
            } else {
                fileTemp.append(field.charAt(i));
            }
        }
        return fileTemp.toString();
    }

    private static void init() throws Exception {
        initDBType();
        initProperties();
        initDBDriver();
    }

    private static void initDBDriver() throws ClassNotFoundException {
        //加载MySql的驱动类
        Class.forName(driverName);
    }

    private static void initProperties() throws Exception {
        //加载jdbc配置文件
        String jdbcfilePath = classesPath + "/jdbc.properties";
        InputStream in = new FileInputStream(jdbcfilePath);
        Properties properties = new Properties();
        SystemProperties properties1 = new SystemProperties();
        properties.load(in);
        in.close();
        driverName = properties.getProperty("jdbc.driverClassName");
        url = properties.getProperty("jdbc.url");
        username = properties.getProperty("jdbc.username");
        password = properties.getProperty("jdbc.password");

        //加载gencode配置文件
        String gencodefilePath = classesPath + "/gencode.properties";
        in = new FileInputStream(gencodefilePath);
        properties = new Properties();
        properties.load(in);
        in.close();
        basePackage = properties.getProperty("gencode.basePackage");
        modulesPrefix = properties.getProperty("gencode.modulesPrefix");
        tableNameList = properties.getProperty("gencode.tableNameList").split(",");

    }

    private static void initDBType() {
        dbType2JavaType.put("int", "Integer");
        dbType2JavaType.put("varchar", "String");
        dbType2JavaType.put("bigint", "Long");
        dbType2JavaType.put("tinyint", "Integer");
        //以is开头的tinyint定义为bool
        dbType2JavaType.put("istinyint", "Boolean");
        dbType2JavaType.put("text", "String");

        dbType2JdbcType.put("int", "INTEGER");
        dbType2JdbcType.put("varchar", "VARCHAR");
        dbType2JdbcType.put("bigint", "BIGINT");
        dbType2JdbcType.put("tinyint", "TINYINT");
        //以is开头的tinyint定义为bool
        dbType2JdbcType.put("istinyint", "BOOLEAN");
        dbType2JdbcType.put("text", "VARCHAR");
    }

    /**
     * 生成文件的存放路径
     *
     * @param type 1-entity，2-mapper，3-dao，4-service，5-serviceimpl，6-controller
     * @return
     */
    private static String getFilePath(int type, String resourceName, String modelName) {
        switch (type) {
            case 1:
                return basePath + "/" + modulesPrefix + "-dao/src/main/java/" + basePackage.replace(".", "/") + "/" + resourceName + "/entity/" + modelName + "Entity.java";
            case 2:
                return basePath + "/" + modulesPrefix + "-dao/src/main/resources/mapper/mapper_" + resourceName + ".xml";
            case 3:
                return basePath + "/" + modulesPrefix + "-dao/src/main/java/" + basePackage.replace(".", "/") + "/" + resourceName + "/dao/I" + modelName + "Dao.java";
            case 4:
                return basePath + "/" + modulesPrefix + "-service/src/main/java/" + basePackage.replace(".", "/") + "/" + resourceName + "/service/I" + modelName + "Service.java";
            case 5:
                return basePath + "/" + modulesPrefix + "-service/src/main/java/" + basePackage.replace(".", "/") + "/" + resourceName + "/service/impl/" + modelName + "ServiceImpl.java";
            case 6:
                return basePath + "/" + modulesPrefix + "-web/src/main/java/" + basePackage.replace(".", "/") + "/" + resourceName + "/controller/" + modelName + "Controller.java";
            default:
                return null;
        }

    }
}
