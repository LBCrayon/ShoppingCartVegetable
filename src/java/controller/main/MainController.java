/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.main;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vapeshop.user.UserDTO;

/**
 *
 * @author Admin
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String HOME = "home.jsp";
    private static final String VIEWCAR = "ViewCarController";
    private static final String VIEWDASHBOARD = "DashboardController";
    private static final String REGISTER = "register.jsp";
    private static final String SIGNIN = "SigninController";
    private static final String CREATE_AN_ACCOUNT = "CreateAccountController";
    private static final String SIGNOUT = "SignoutController";
    private static final String SEARCH = "SearchController";
    private static final String DELETE = "DeleteVegetableController";
    private static final String LAUNCH = "LaunchVegetableController";
    private static final String UPDATE = "UpdateController";
    private static final String ADD_TO_CART = "AddToCartController";
    private static final String REMOVE_FROM_CART = "RemoveFromCartController";
    private static final String UPDATE_CART = "UpdateCartController";
    private static final String VIEW_CART = "viewCart.jsp";
    private static final String CHECK_OUT = "CheckOutController";

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
        UserDTO user = (UserDTO) session.getAttribute("USER");

        try {
            String action = request.getParameter("action");
            
            if ("Sign In".equals(action)) {
                url = SIGNIN;
            } else if ("Create".equals(action)) {
                url = CREATE_AN_ACCOUNT;
            } else if ("Home".equals(action)) {
                url = HOME;
            } else if ("ViewCar".equals(action)) {
                url = VIEWCAR;
            } else if ("GotoRegister".equals(action)) {
                url = REGISTER;
            } else if ("Search".equals(action)) {
                url = SEARCH;
            } else if (user == null) {
                request.setAttribute("ERROR", "Please sign in to use that function");
                url = HOME;
            } else {
                if (user.getRoleID().equals("US001")) {
                    if ("ViewCar".equals(action)) {
                        url = VIEWCAR;
                    } else if ("ViewCart".equals(action)) {
                        url = VIEW_CART;
                    } else if ("Add".equals(action)) {
                        url = ADD_TO_CART;
                    } else if ("Remove".equals(action)) {
                        url = REMOVE_FROM_CART;
                    } else if ("Update".equals(action)) {
                        url = UPDATE_CART;
                    } else if ("Check Out".equals(action)) {
                        url = CHECK_OUT;
                    }
                }
                if (user.getRoleID().equals("AD002")) {
                    if ("ViewDashboard".equals(action)){
                        url = VIEWDASHBOARD;
                    } else if ("Delete".equals(action)) {
                        url = DELETE;
                    } else if ("Launch".equals(action)) {
                        url = LAUNCH;
                    } else if ("Update".equals(action)) {
                        url = UPDATE;
                    }
                }
                if ("Sign out".equals(action)) {
                    url = SIGNOUT;
                }
            }
        } catch (Exception e) {
            log("MainController error at: " + e.toString());
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
