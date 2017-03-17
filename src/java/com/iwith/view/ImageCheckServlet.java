/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iwith.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
//@WebServlet(name = "ImageCheckServlet", urlPatterns = {"/images/check.jpg"})
public class ImageCheckServlet extends HttpServlet {

    private int len = 6;

    public void init() {
        String length = this.getInitParameter("length");
        if (length != null && length.matches("\\d+")) {
            int len = Integer.parseInt(length);
            this.len = len;
        }
    }

    private Color getRandomColor(int fc, int bc) {
        //在參數設定的範圍內，隨機產生顏色
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public BufferedImage generateImage(String rand, int width, int height) {
        //開始建立影像
        BufferedImage image
                = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();   //取得影像繪圖區
        g.setColor(getRandomColor(200, 250)); //設定繪圖區背景色
        g.fillRect(0, 0, width, height);  //在繪圖區畫個長方型

        //產生干擾線讓影像略模糊不易識別
        Random random = new Random();
        g.setColor(getRandomColor(170, 210));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        //將認證數字顯示到影像
        g.setFont(new Font("Arial", Font.PLAIN, 18));//設定字體
        g.setColor(getRandomColor(20, 140));
        //將認證數字顯示到影像
        g.drawString(rand, 110, 30);

        g.dispose();
        return image;
    }

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
        //1. [從session取得]/產生[放入session]6碼的大寫英數字字串        
        String get = request.getParameter("get");        
        String rand = null;
        if(get==null){//不更新圖片
            rand = (String) session.getAttribute(this.getServletName());
        }else{//要更新圖片
            session.removeAttribute(this.getServletName());
        }
        
        if (rand == null) {
            Random r = new Random();
            rand = "";
            for (int i = 0; i < len; i++) {
                int data = r.nextInt(35);
                char aChar = (data < 10) ? (char) (data + '0') : (char) (data + 'A' - 10);
                System.out.println("aChar = " + aChar);
                rand += aChar;
            }

            session.setAttribute(this.getServletName(), rand);
        }
        
        int w = 16 * 2 + 12 * len;
        System.out.println("rand = " + rand);

        //2. 把字串變成圖
        BufferedImage image = generateImage(rand, 270, 47);

        //3. 將圖片以JPG格式輸出至http response
        response.setContentType("image/jpeg");
        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(image, "JPEG", out);
        }
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
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
