package cn.usts.util.netInterceptor;


import cn.usts.util.session.SessionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * @Author: ${朱朝阳}
 * @Date: 2019/7/18 12:28
 */
@Slf4j
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;


        boolean flag = true;

        String requestUrl = request.getRequestURI();

        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            // 所有的跨域OPTIONS请求直接放行
            flag = true;
        } else {
            if (requestUrl.equals("/TeachingManagement/batch/import/teachers.do") ||
                    requestUrl.equals("/TeachingManagement/marjor/batch/template/export.do") ||
                    requestUrl.equals("/TeachingManagement/marjor/batch/template/import.do") ||
                    requestUrl.equals("/TeachingManagement/marjor/batch/import.do") ||
                    requestUrl.equals("/TeachingManagement/websocket.do") ||
                    requestUrl.equals("/TeachingManagement/user/query.do") ||
                    requestUrl.equals("/TeachingManagement/batch/down/teacher/template.do") ||
                    requestUrl.equals("/") ||
                    requestUrl.equals("/TeachingManagement") ||
                    requestUrl.equals("/TeachingManagement/file/fileUpload.do") ||
                    requestUrl.equals("/TeachingManagement/form/singalDownload.do") ||
                    requestUrl.equals("/TeachingManagement/file/deleteFile.do") ||
                    requestUrl.equals("/TeachingManagement/out/system.do") ||
                    requestUrl.equals("/TeachingManagement/form/screen.do")) {
                // 登录请求直接放行 或者 Tomcat加载 或者Upload上传文件 或者 下载文件  或者 注销
                flag = true;
            } else {
                String usertoken = request.getHeader("usertoken");
                SessionContext sessionContext = SessionContext.getInstance();
                HttpSession session = sessionContext.getSession(usertoken);
                if (session == null) {
                    // 登录过期不予放行
                    flag = false;
                }
            }
        }

        if (flag) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.getRequestDispatcher("/session/out.do").forward(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }
}
