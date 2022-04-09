/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vapeshop.cart.CartDTO;
import vapeshop.order.OrderDAO;
import vapeshop.user.UserDTO;
import java.io.PrintWriter;

/**
 *
 * @author Admin
 */
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "viewCart.jsp";
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
            double total = Double.parseDouble(request.getParameter("total"));
            OrderDAO dao = new OrderDAO();
            if (user.getRoleID().equals("US001")) {
                boolean check1 = dao.CreateOrder(user, total);
                if (check1) {
                    int orderID = dao.GetNewestOrderID();
                    boolean check2 = dao.CreateOrderDetails(orderID, cart);
                    if (check2) {
                        request.setAttribute("MESSAGE", "Check out success");
                        session.removeAttribute("CART");
                        url = SUCCESS;
                    }
                }
            } else {
                url = IS_ADMIN;
            }
        } catch (Exception e) {
            log("CheckOutController error at: " + e.toString());
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
