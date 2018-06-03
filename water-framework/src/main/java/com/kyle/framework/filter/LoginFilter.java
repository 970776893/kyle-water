package com.kyle.framework.filter;

import com.alibaba.fastjson.JSON;
import com.kyle.framework.model.ModelResult;
import com.kyle.framework.model.ReturnCodeEnum;
import com.kyle.framework.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author -- zhangkai02
 *         6/3/18 18:50
 */
@Log4j2
public class LoginFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        Object userCode = session.getAttribute(Constants.LOGIN_SESSION_ATTRIBUTE_KEY);
        System.out.println(httpServletRequest.getSession().getMaxInactiveInterval());
        if(userCode == null){
            log.info("未登录");
            ModelResult<String> noLoginResult = new ModelResult<>("未登录/登录已失效");
            noLoginResult.setCode(ReturnCodeEnum.NO_LOGIN.code);
            String message = JSON.toJSON(noLoginResult).toString();
            httpServletResponse.getOutputStream().write(message.getBytes(httpServletResponse.getCharacterEncoding()));
            httpServletResponse.setContentType("application/json");
            httpServletRequest.getSession().setAttribute(Constants.LOGIN_SESSION_ATTRIBUTE_KEY, "1");
        }else{
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }
}
