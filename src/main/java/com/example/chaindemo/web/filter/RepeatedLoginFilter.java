package com.example.chaindemo.web.filter;

import com.example.chaindemo.exception.AuthenticationException;
import com.example.chaindemo.web.Constant;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author yuandongfei
 * @date 2019/5/13
 */
public class RepeatedLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Object exception = request.getAttribute(Constant.FILTER_EXCEPTION_HANDLER_KEY);
        if (!Objects.isNull(exception)) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            // 获取页面传入的token
            HttpServletRequest req = (HttpServletRequest) request;
            String reqToken = req.getHeader("token");
            String isDebug = req.getHeader("debug");
            if (!StringUtils.isEmpty(isDebug)) {
                filterChain.doFilter(request, response);
                return;
            }
            if (StringUtils.isEmpty(reqToken)) {
                throw new AuthenticationException("未获取验证重复提交参数token的值");
            }
            HttpSession session = req.getSession();
            // 获取session存储的token
            Object attribute = session.getAttribute(Constant.TOKEN_SESSION_KEY);
            if (null == attribute) {
                // session 为空，可以视为非法登录请求
                throw new AuthenticationException("非法登录请求");
            }
            // 判断两个token是否一致
            String token = ((String) attribute);
            if (!token.equalsIgnoreCase(reqToken)) {
                // request token 和 session token 不匹配，则认为非法请求
                throw new AuthenticationException("token校验不匹配");
            } else {
                // 删除这次的登录token
                session.removeAttribute(Constant.TOKEN_SESSION_KEY);
            }
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e) {
            request.setAttribute(Constant.FILTER_EXCEPTION_HANDLER_KEY, e);
            request.getRequestDispatcher("/500").forward(request, response);
        }
    }
}
