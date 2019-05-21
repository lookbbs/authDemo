package com.example.chaindemo.web.log;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author yuandongfei
 * @date 2019/5/21
 */
public class HttpServletRequestReplacedFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            ServletRequest requestWrapper = new RequestReaderHttpServletRequestWrapper((HttpServletRequest) request);
            //获取请求中的流，将取出来的字符串，再次转换成流，然后把它放入到新request对象中。
            // 在chain.doFiler方法中传递新的request对象
            chain.doFilter(requestWrapper, response);
            return;
        }
        chain.doFilter(request, response);
    }
}
