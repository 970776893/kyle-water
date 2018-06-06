package com.kyle.framework.filter;

import com.alibaba.fastjson.JSON;
import com.kyle.framework.model.ModelResult;
import com.kyle.framework.model.ReturnCodeEnum;
import com.kyle.framework.utils.Constants;
import com.kyle.framework.utils.PatternMatchUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * @author -- zhangkai02
 *         6/3/18 18:50
 */
@Log4j2
public class LoginFilter extends OncePerRequestFilter {

    private Set<String> excludeFilters;

    public void setExcludeConfigLocation(String configLocation) throws IOException {
        File file = ResourceUtils.getFile(configLocation);
        FileInputStream fileIn = new FileInputStream(file);
        Properties properties = new Properties();
        properties.load(fileIn);
        excludeFilters = properties.stringPropertyNames();
        fileIn.close();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        boolean filter = isDoFilter(httpServletRequest);
        if (!filter) {
            log.info("未登录");
            ModelResult<String> noLoginResult = new ModelResult<>("未登录/登录已失效");
            noLoginResult.setCode(ReturnCodeEnum.NO_LOGIN.code);
            String message = JSON.toJSON(noLoginResult).toString();
            httpServletResponse.getOutputStream().write(message.getBytes(httpServletResponse.getCharacterEncoding()));
            httpServletResponse.setContentType("application/json");
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private boolean isDoFilter(HttpServletRequest httpServletRequest) {
        boolean isExclude = exculded(httpServletRequest);
        if (isExclude) {
            return true;
        }
        boolean isLogined = loggined(httpServletRequest);
        if (isLogined) {
            return true;
        }
        return false;
    }


    private boolean loggined(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Object userInfo = session.getAttribute(Constants.LOGIN_SESSION_ATTRIBUTE_KEY);
        return userInfo != null;
    }

    private boolean exculded(HttpServletRequest httpServletRequest) {
        if (CollectionUtils.isEmpty(excludeFilters)) {
            return false;
        }
        String servletPath = httpServletRequest.getServletPath();
        for (String excludeFileter : excludeFilters) {
            boolean isMatch = PatternMatchUtils.match(excludeFileter, servletPath);
            if (isMatch) {
                return true;
            }

        }
        return false;
    }
}
