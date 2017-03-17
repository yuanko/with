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
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Set;
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
//@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/update_cart.do"})
//public class UpdateCartServlet extends HttpServlet {
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
//        //0.從session取得cart
//        ShoppingCart cart = (ShoppingCart) request.getSession().getAttribute("cart");
//        if (cart != null && cart.size() > 0) {
//            Set<Product> trash = new HashSet<>();//建立trash作為[待刪除品項]的暫存區
//            for (Product p : cart.getProductsSet()) {
//                //1. 取得form data: delete_pid_x, quantity_pid_x
//                String delete = request.getParameter("delete_pid_"+p.getId());
//                //2. 檢查是否刪除商品
//                if(delete==null){//否
//                    //檢查要修改的數量
//                    String quantity = request.getParameter("quantity_pid_"+p.getId());
//                    if(quantity!=null && quantity.matches("\\d+")){
//                    //3.1 商業邏輯: 修改cart中的商品數量
//                        int q = Integer.parseInt(quantity);
//                        cart.update(p, q);
//                    }
//                }else{
//                    //3.2 商業邏輯: 刪除cart中的商品
//                    trash.add(p);//先移入垃圾桶
//                    //cart.remove(p);
//                }
//            }
//            
//            for(Product p:trash){//依據垃圾桶中暫存的商品來移除cart中應刪除的品項
//                cart.remove(p); 
//            }
//        }
//
//        //4. redirect到cart.jsp
//        response.sendRedirect(request.getContextPath() + "/cart.jsp");
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
////    @Override
////    protected void doGet(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        processRequest(request, response);
////    }
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
