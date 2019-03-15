/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managedBean.AuthenticationManagedBean;
import managedBean.HotelManagedBean;

/**
 *
 * @author fengl
 */
public class AuthenticationFilter implements Filter {

    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    public AuthenticationFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request1 = (HttpServletRequest) request;
        AuthenticationManagedBean bean;
        HotelManagedBean hotelManagedBean;
        HttpSession session = request1.getSession(false);
        if (session == null) {
            bean = null;
            hotelManagedBean = null;
        } else {
            bean = (AuthenticationManagedBean) session.getAttribute("authenticationManagedBean");
            hotelManagedBean = (HotelManagedBean) session.getAttribute("hotelManagedBean");
        }

        if (bean == null || bean.getId() == -1L) {
            ((HttpServletResponse) response).sendRedirect(request1.getContextPath() + "/login.xhtml");
        } else {
            chain.doFilter(request1, response);
        }

    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {

    }

}
