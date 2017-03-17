package com.iwith.controller;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.verygoodbook.controller;
//
//import com.verygoodbook.entity.Product;
//import com.verygoodbook.entity.ShoppingCart;
//import com.verygoodbook.service.ProductService;
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author Administrator
// */
//@WebServlet(name = "AddCartServlet", urlPatterns = {"/add_cart.do"})
//public class AddCartServlet extends HttpServlet {
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
//        //1.get parameter: pid, quantity, color
//        String pid = request.getParameter("pid");
//        String quantity = request.getParameter("quantity");
//        String color = request.getParameter("color");
//        
//        //2. 檢查資料
//        if (pid != null && pid.matches("\\d+")) {
//            int id = Integer.parseInt(pid);
//            int q = 1; 
//            if(quantity!=null && quantity.matches("\\d+")){
//                q = Integer.parseInt(quantity);
//                if(q<=0) q = 1;
//            }
//            
//            //3. 執行商業邏輯
//            ProductService service = new ProductService();
//            try {
//                Product p = service.get(id);
//                if (p != null) {
//                    ShoppingCart cart = (ShoppingCart)
//                            request.getSession().getAttribute("cart");
//                    if (cart == null) {
//                        cart = new ShoppingCart();
//                        request.getSession().setAttribute("cart", cart);
//                    }
//                    if(color==null || 
//                        (color!=null && p.getColors().contains(color))){
//                        p.setColor(color);
//                    }
//                    cart.add(p, q);
//                }
//            } catch (Exception ex) {
//                this.log("add cart fail: Product Id- " + id, ex);
//            }            
//        }
//        
//        //4.Redirect到購物車畫面
//        response.sendRedirect(request.getContextPath()+"/cart.jsp");
//        return;
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
