package com.example.chaindemo.web.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 定义日志切面
 *
 * @author yuandongfei
 * @date 2019/5/21
 */
@Slf4j
public class LogHandlerInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            LogAnnotation annotation = ((HandlerMethod) handler).getMethodAnnotation(LogAnnotation.class);
            if (null != annotation) {
                String message = annotation.message();
                String name = annotation.opt().name();
                String method = request.getMethod();
                String param;
                if (!StringUtils.equalsIgnoreCase(HttpMethod.GET.name(), method)) {
                    param = HttpHelper.getBodyString(request);
                } else {
                    Map<String, String[]> parameterMap = request.getParameterMap();
                    param = objectMapper.writeValueAsString(parameterMap);
                }
                log.info(">>> 用户执行了【{}】操作，消息内容：{}，参数列表：{}", name, message, param);
            }
        }
        // 只打印日志，所以永远返回true，不做拦截
        return true;
    }
}
