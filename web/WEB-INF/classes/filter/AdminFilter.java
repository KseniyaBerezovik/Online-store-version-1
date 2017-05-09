package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/admin", "/addProduct", "/addNewProduct", "/seeAllClients", "/seeAllProducts", "/seeOrdersByClient", "/seeOrdersByProduct"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            Object isAdmin = httpServletRequest.getSession().getAttribute("isAdmin");
            if(isAdmin != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
                (httpServletResponse).sendRedirect("/catalog");
            }
        }
    }

    @Override
    public void destroy() {}
}