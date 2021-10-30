package cn.fishland.javaweb.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 关于admin后台管理的过滤
 *
 * @author fishland
 * @version 1.0
 * @date 2021/10/30 10:30 下午
 */
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String uri = req.getRequestURI();

        if ("/JavaWeb/admin/login".equals(uri)) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession();

            Object user = session.getAttribute("user");
            if (user != null) {
                chain.doFilter(request, response);
            }

            request.getRequestDispatcher("/WEB-INF/web/admin/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
