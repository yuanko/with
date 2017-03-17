package com.iwith.controller;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.verygoodbook.controller;
//
//import com.verygoodbook.entity.Customer;
//import com.verygoodbook.entity.VIP;
//import com.verygoodbook.exception.VGBException;
//import com.verygoodbook.service.CustomerService;
//import com.verygoodbook.service.RDBConnection;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// *
// * @author Administrator
// */
//@WebServlet(name = "UpdateServlet", urlPatterns = {"/member/update.do"})
//public class UpdateServlet extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        List<String> errors = new ArrayList<>();
//        Customer member = (Customer) request.getSession().getAttribute("member");
//        if(member==null){
//            response.sendRedirect(request.getContextPath()+"/login.jsp");
//            return;
//        }
//
//        //1. 取得/member/update.jsp中的表單資料:userid, password, password1, password2, name, email, gender, ....        
//        request.setCharacterEncoding("UTF-8");
//        String userid = member.getId();
//        String password = request.getParameter("password");
//        String password1 = request.getParameter("password1");
//        String password2 = request.getParameter("password2");
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String gender = request.getParameter("gender"); //M, F   
//
//        //birthday, phone, address, married...
//        String birthday = request.getParameter("birthday");
//        String phone = request.getParameter("phone");
//        String address = request.getParameter("address");
//        String married = request.getParameter("married");
//        String checkCode = request.getParameter("checkCode");
//
//        //2. 檢查資料
//        if(password==null || !password.equals(member.getPassword())){
//            errors.add("必須輸入正確的原密碼!");
//        }
//        
//        //修改密碼的對應檢查
//        if (password1 != null && password1.length() > 0
//                && password2 != null && password2.length() > 0) {
//            if (!password1.equals(password2)) {
//                errors.add("會員密碼與確認密碼必須一致");
//            }
//        }else{
//            password1 = password;
//        }
//
//        if (name == null || (name = name.trim()).length() == 0) {
//            errors.add("必須輸入姓名");
//        }
//
//        if (email == null || (email = email.trim()).length() == 0) {
//            errors.add("必須輸入電子郵件");
//        }
//
//        if (gender == null || (gender = gender.trim()).length() == 0) {
//            errors.add("必須輸入會員性別");
//        }
//
//        HttpSession session = request.getSession();
//        if (checkCode == null || (checkCode = checkCode.trim()).length() == 0) {
//            errors.add("必須輸入驗證碼");
//        } else {
//
//            String registerImageCheckServlet = (String) session.getAttribute("RegisterImageCheckServlet");
//            if (!checkCode.equalsIgnoreCase(registerImageCheckServlet)) {
//                errors.add("驗證碼不正確");
//            }
//        }
//
//        if (errors.isEmpty()) {
//            try {
//                //3. 呼叫商業邏輯
//                
//                Customer c = member.getClass().newInstance();
//                
//                c.setId(userid);
//                c.setPassword(password1);
//                c.setEmail(email);
//                c.setName(name);
//                c.setGender(gender.charAt(0));
//
//                if(phone!=null) c.setPhone(phone);
//                if(address!=null) c.setAddress(address);
//                if(birthday!=null) c.setBirthday(birthday);
//                c.setMarried(married != null);
//                if(member instanceof VIP){
//                    ((VIP) c).setDiscount(((VIP)member).getDiscount());
//                }
//
//                CustomerService service = new CustomerService();
//                service.update(c);
//
//                session.removeAttribute("RegisterImageCheckServlet");
//                //4.1 註冊成功，將控制權redirect給HOME Page
//                session.setAttribute("member", c);//將修改後的會員資料存回session
//                response.sendRedirect(request.getContextPath());
//                return;
//
//            } catch (VGBException ex) {
//                if (ex.getCause() != null && ex.getCause() instanceof SQLException) {
//                    int errorCode = ((SQLException) ex.getCause()).getErrorCode();
//                    if (errorCode == RDBConnection.DUPLICATE_ENTRY_ERROR_CODE) {
//                        errors.add("會員註冊失敗: 鍵值(身分證號/Email)重複!");
//                    } else {
//                        errors.add(ex.getCause().getMessage()
//                                + ((SQLException) ex.getCause()).getErrorCode());
//                    }
//
//                } else {
//                    errors.add(ex.toString());
//                }
//            } catch (Exception ex) {
//                this.log("會員註冊失敗-系統發生非預期錯誤!", ex);
//                errors.add("會員註冊失敗-系統發生非預期錯誤:" + ex.toString());
//            }
//        }
//
//        System.out.println("errors:" + errors);
//        //4.2 註冊失敗，將控制權forward給/member/update.jsp(errors List)
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/member/update.jsp");
//        request.setAttribute("errors", errors);
//        dispatcher.forward(request, response);
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
