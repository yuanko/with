/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iwith.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrator
 */
public class PerformanceFilter implements Filter {
    private FilterConfig filterConfig;
    private static final String defaultPrefix = "Perforamnce: ";
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String prefix = filterConfig.getInitParameter("prefix");
        if(prefix==null){
            prefix = defaultPrefix;
        }
        
        long begin = System.currentTimeMillis();
        
        chain.doFilter(request, response);
        
        long end = System.currentTimeMillis();
        
        StringBuffer sb = new StringBuffer(prefix);
        sb.append(((HttpServletRequest)request).getRequestURI());
        sb.append("共花了");
        sb.append(end-begin);
        sb.append(" ms");
        
        this.filterConfig.getServletContext().log(sb.toString());
    }

    @Override
    public void destroy() {
    }
}
