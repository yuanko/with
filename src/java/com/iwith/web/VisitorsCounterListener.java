/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iwith.web;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 *
 * @author Administrator
 */
@WebListener
public class VisitorsCounterListener implements ServletContextListener, HttpSessionListener {
    private String filePath = "/WEB-INF/vgb.properties";
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //TODO:        
        System.out.println("context Initialized");        
        ServletContext application = sce.getServletContext();
        String path = application.getRealPath(filePath);
        System.out.println("path = " + path);
        Properties props = new Properties();
        
        try (FileReader reader = new FileReader(path)){
            props.load(reader);
            application.log("讀取系統設定檔案["+path+"]成功");
        } catch (IOException ex) {
            application.log("讀取系統設定檔案["+path+"]失敗", ex);
        }
        
        Enumeration names =  props.propertyNames();
        while(names.hasMoreElements()){
            String name = (String)names.nextElement();
            String value = props.getProperty(name);
            if(value!=null && value.matches("\\d+")){
                application.setAttribute(name, Integer.parseInt(value));
            }else{            
                application.setAttribute(name, value);
            }
        }
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("context Destroyed");
        ServletContext application = sce.getServletContext();        
        Properties props = new Properties();
        
        Enumeration<String> names = application.getAttributeNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            if(name.indexOf("app.")>-1){
                Object value = application.getAttribute(name);            
                props.setProperty(name, value.toString());
            }
        }
        
        //FileWriter
        String path = application.getRealPath(filePath);
        try (FileWriter writer = new FileWriter(path);){
            props.store(writer, "VGB context Destroyed: store Application attributes!");
            application.log("寫入系統設定檔案["+path+"]成功");
        } catch (IOException ex) {
            application.log("寫入系統設定檔案["+path+"]失敗", ex);
        }
    } 

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext application = se.getSession().getServletContext();
        Integer counter = (Integer) application.getAttribute("app.visitors.counter");
        if (counter == null) {
            counter = 16800;
        }
        application.setAttribute("app.visitors.counter", ++counter);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        
    }

}
