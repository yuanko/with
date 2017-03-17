/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iwith.web;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 *
 * @author Administrator
 */
@WebFilter(filterName = "CharsetFilter", urlPatterns = {"*.jsp", "*.view", "*.do"}, 
        initParams = {@WebInitParam(name = "charset", value = "UTF-8")}, 
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.ERROR} )
public class CharsetFilter implements Filter {
    private static final String default_charset = "UTF-8";
    private FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String charset = this.filterConfig.getInitParameter("charset");
        if (charset==null){
            charset = default_charset;
        }
        
        request.setCharacterEncoding(charset);
        request.getParameterNames();
        
        response.setCharacterEncoding(charset);
        response.getWriter();        
        
        //以上程式為前置處理...
        chain.doFilter(request, response);
        //以下程式為後續處理...        
    }

    @Override
    public void destroy() {}    
}
