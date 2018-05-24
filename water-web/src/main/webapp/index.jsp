<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%
    //根据url前缀区分是所用的环境
    String staticHost = "http://" + request.getHeader("host") + ":9000";
%>
<!DOCTYPE html>
<html lang="zh-CN" ng-app="baseFontApp">
<head>
    <meta charset="utf-8" />
    <title>easyShopping</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <script src="<%=staticHost %>/js/jquery/1.12.0/jquery.min.js"></script>
    <%-- angular --%>
    <script src="<%=staticHost %>/js/angular/1.5.0-rc.1/angular.min.js"></script>
    <script src="<%=staticHost %>/js/angular/1.5.0-rc.1/angular-route.min.js"></script>
    <script src="<%=staticHost %>/js/angular/1.5.0-rc.1/angular-animate.min.js"></script>
    <script src="<%=staticHost %>/js/angular/1.5.0-rc.1/angular-cookies.min.js"></script>
    <script src="<%=staticHost %>/js/angular/1.5.0-rc.1/angular-touch.min.js"></script>
    <script src="<%=staticHost %>/js/angular/1.5.0-rc.1/angular-sanitize.min.js"></script>
    <script src="<%=staticHost %>/js/angular/1.5.0-rc.1/angular-resource.min.js"></script>
    <%-- ui-bootstrap --%>
    <script src="<%=staticHost %>/js/angular-ui-bootstrap/1.2.0/ui-bootstrap-tpls.min.js"></script>
    <script src="<%=staticHost %>/js/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="<%=staticHost %>/js/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <%-- awesome 图像集 --%>
    <link rel="stylesheet" href="<%=staticHost %>/js/font-awesome/3.2.1/font-awesome.min.css"/>
    <%-- datatimepicker 日期时间选择框 --%>
    <script src="<%=staticHost %>/js/moment/2.10.6/moment.min.js"></script>
    <script src="<%=staticHost %>/js/moment/2.10.6/moment-with-locales.min.js"></script>
    <script src="<%=staticHost %>/js/angular-bootstrap-datetimepicker/1.0.1/js/datetimepicker.js"></script>
    <script src="<%=staticHost %>/js/angular-bootstrap-datetimepicker/1.0.1/js/datetimepicker.templates.js"></script>
    <link rel="stylesheet" href="<%=staticHost %>/js/angular-bootstrap-datetimepicker/1.0.1/css/datetimepicker.css"/>
    <%-- flash提示框 --%>
    <script src="<%=staticHost %>/js/angular-flash/angular-flash.min.js"></script>
    <link rel="stylesheet" href="<%=staticHost %>/js/angular-flash/angular-flash.min.css"/>
    <%-- 文件上传 --%>
    <script src="<%=staticHost %>/js/angular-file-upload/3.0.0-alpha/angular-file-upload.min.js"></script>
    <%--
            RSA加密
        	var rsa = new RSAKey();
  	        rsa.setPublic('93e959b8251cde21edbef872224ec19f687fc191624d48fe8bcd629bf1c8dd9b00f65b31935387040301e73d2021b563e8a3098fe9a3e09de9d2285a247832a3e34c0e78f05156de2484577c27427dd350ed7a0bf15538b881b6a2f8ef335ff874c859eaab995b516d165cc52edc0658f5c03654997db430eeb76d731f71d4f3', '10001');
  	        var res = rsa.encrypt('1d23adf45a');
    --%>
    <script src="<%=staticHost %>/js/security/jsbn.js"></script>
    <script src="<%=staticHost %>/js/security/prng4.js"></script>
    <script src="<%=staticHost %>/js/security/rng.js"></script>
    <script src="<%=staticHost %>/js/security/rsa.js"></script>
    <%--
        MD5
        md5("12345678")
    --%>
    <script src="<%=staticHost %>/js/security/md5.js"></script>
    <%-- 图标 --%>
    <link rel="shortcut icon" href="<%=staticHost %>/app/imgs/favicon_min.jpg" type="image/x-icon" />
    <%-- 本地文件js和css --%>
    <script src="<%=staticHost %>/dist/js/baseFont.js"></script>
    <link rel="stylesheet" href="<%=staticHost %>/dist/css/baseFont.min.css"/></head>
</head>
<body cs-layout></body>
</html>