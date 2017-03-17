/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iwith.controller;


//import com.iwith.entity.Customer;
//import com.iwith.exception.VGBException;
//import com.iwith.service.CustomerService;
import com.iwith.entity.Customer;
import com.iwith.exception.VGBException;
import com.iwith.service.CustomerService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login.do"})//url路徑前要有/
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //1. 讀取請求傳來的表單資料: userid, password, checkCode
        //request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String auto = request.getParameter("auto");//是否自動記住帳號
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");

        //2. 檢查資料
        List<String> errors = new ArrayList<>();
        if (email == null || (email=email.trim()).length() == 0) {//null或空白
            errors.add("必須輸入Email帳號");
        }

        if (password == null || (password=password.trim()).length() == 0) {
            errors.add("必須輸入密碼");
        }
        
        if (checkCode == null || (checkCode=checkCode.trim()).length() == 0) {
            errors.add("必須輸入驗證碼");
        }else{
            String rand = (String)session.getAttribute("ImageCheckServlet");
            if(!checkCode.equalsIgnoreCase(rand)){
                errors.add("驗證碼不正確");
            }
        }

        if (errors.isEmpty()) {
            //session.removeAttribute("ImageCheckServlet");
            //3. 呼叫執行商業邏輯CustomerService login        
            CustomerService service = new CustomerService();
            try {
                Customer c = service.login(email, password);
                
                session.removeAttribute("ImageCheckServlet");//登入後刪除驗證碼
                
                //以下為寫入Cookie的示範
                Cookie emailCookie = new Cookie("email", email);
                Cookie autoCookie = new Cookie("auto", "checked");
                
                if(auto!=null){//要記住帳號
                    emailCookie.setMaxAge(30*24*60*60);//30天
                    autoCookie.setMaxAge(30*24*60*60);
                }else{
                    emailCookie.setMaxAge(0);//立刻失效
                    autoCookie.setMaxAge(0);
                }
                
                response.addCookie(emailCookie);
                response.addCookie(autoCookie);
                //以上為寫入Cookie的示範
                
                //4.1(old) forward畫面控制權給首頁
                //session.setAttribute("member", c);
                //RequestDispatcher dispatcher = request.getRequestDispatcher("/");
                //dispatcher.forward(request, response);    
                //return;
                
                //session.setMaxInactiveInterval(30); //30 sec.
                //4.1(new) redirect畫面控制權給首頁或previous.page   
                System.out.println("member = " + c);
                session.setAttribute("member", c);//確認後直接登入
                String previousURL = (String)session.getAttribute("previous.page");
                session.removeAttribute("previous.page");
                response.sendRedirect(previousURL!=null?previousURL:request.getContextPath()+"/product-list.jsp");                
                
                return;
            } catch (VGBException ex) {                     
                errors.add(ex.getMessage());
            } catch (Exception ex){
                this.log("客戶[登入]發生非預期錯誤", ex);
                errors.add(ex.getMessage());
            }
        }
        
        //4.2 將畫面控制權完全forward給login.jsp
        RequestDispatcher dispatcher = 
            request.getRequestDispatcher("/");
        
        request.setAttribute("errors", errors);
        dispatcher.forward(request, response);                
        return;
        
        //81~104: 原來Servlet自行輸出錯誤畫面的程式碼
        //response.setContentType("text/html;charset=UTF-8");
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = null;
//        try {
//            /* TODO output your page here. You may use following sample code. */
//            out = response.getWriter();
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>登入失敗</title>");
//            out.println("<meta charset='UTF-8'>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>登入失敗</h1>");
//            out.println("<p>"+ errors + "</p>");
//            out.println("<input type='button' value='back' onclick='history.back();'>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {
//            if (out != null) {
//                out.close();
//            }
//        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
