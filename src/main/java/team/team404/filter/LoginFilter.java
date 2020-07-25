package team.team404.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();

/*        if(session.getAttribute("_CURRENT_USER") != null){
            arg2.doFilter(arg0, arg1);
            return;
        }
        if(request.getRequestURI().indexOf("home.action") != -1 || request.getRequestURI().indexOf("login.action") != -1){
            arg2.doFilter(arg0, arg1);
            return;
        }
        // 没有登录
        response.sendRedirect(request.getContextPath()+"/home.action");*/

        if(session.getAttribute("user")==null){
            // 没有登录
            response.sendRedirect(request.getContextPath()+"/index/mian");
        }else{
            // 已经登录，继续请求下一级资源（继续访问）
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }
}
