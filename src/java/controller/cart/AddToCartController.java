/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cart;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vapeshop.vegetable.VegetableDTO;
import vapeshop.cart.CartDTO;
import vapeshop.user.UserDTO;

/**
 *
 * @author Admin
 */
public class AddToCartController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ViewCarController";
    private static final String IS_ADMIN = "home.jsp";

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
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        HttpSession session = request.getSession();
        CartDTO cart = (CartDTO) session.getAttribute("CART");
        UserDTO user = (UserDTO) session.getAttribute("USER");

        try {
            String vegetableID = request.getParameter("vegetableID");
            String vegetableName = request.getParameter("vegetableName");
            int quanity = 1;
            double price = Double.parseDouble(request.getParameter("price"));
            double sale = Double.parseDouble(request.getParameter("sale"));
            if (user.getRoleID().equals("US001")) {
                if (cart == null) {
                    cart = new CartDTO();
                }
                VegetableDTO vegetable = new VegetableDTO(vegetableID,vegetableName, quanity, price, sale, "", true);
                boolean check = cart.add(vegetable);
                if (check) {
                    session.setAttribute("CART", cart);
                    url = SUCCESS;
                    request.setAttribute("NOTIFY", vegetableName + " have been added to your cart");
                }
            } else {
                url = IS_ADMIN;
            }
        } catch (Exception e) {
            log("AddToCartController error at: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
