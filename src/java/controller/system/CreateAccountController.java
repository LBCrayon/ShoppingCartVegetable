/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vapeshop.user.UserDAO;
import vapeshop.user.UserDTO;
import vapeshop.user.UserErrorDTO;

/**
 *
 * @author Admin
 */
public class CreateAccountController extends HttpServlet {
    private static final String ERROR = "register.jsp";
    private static final String USER_SUCCESS = "home.jsp";

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
        
        try {
            String username = request.getParameter("username");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String password = request.getParameter("password");
            String roleID = "US001";
            String confirm = request.getParameter("confirm");
            
            UserErrorDTO error = new UserErrorDTO();
            boolean fault = false;
            UserDAO dao = new UserDAO();
            
            if(username.length() < 3 || username.length() > 20) {
                error.setUsernameError("Username must be 3 to 20 characters");
                fault = true;
            }
            
            if(fullname.length() < 1 || fullname.length() > 30) {
                error.setFullnameError("Fullname must be 30 characters or less");
                fault = true;
            }
            
            if(!email.matches("^[A-Za-z0-9_\\.]{3,19}@[a-z0-9]{2,10}(\\.[a-z0-9]{2,5}){0,3}$")) {
                error.setEmailError("Wrong email format");
                fault = true;
            }
            
            if(address.length() < 10 || username.length() > 60) {
                error.setAddressError("Address must be 10 to 60 characters");
                fault = true;
            }
            
            if(!phone.matches("^[0-9]*$")) {
                error.setPhoneError("phone must be number");
                fault = true;
            } else if (phone.length() < 8 || phone.length() > 11) {
                error.setPhoneError("phone must be be 8 to 11 digits");
                fault = true;
            }
            
            if(password.length() < 5 || username.length() > 16) {
                error.setPasswordError("Password must be 5 to 16 characters");
                fault = true;
            }
            
            if(!confirm.matches(password)) {
                error.setConfirmError("Confirm must match password");
                fault = true;
            }
            
            if (fault) {
                request.setAttribute("ERROR", error);
            } else {
                UserDTO newUser = new UserDTO(username, fullname, email, password, address, phone, roleID);
                boolean check = dao.creatAccount(newUser);
                if (check) {
                    UserDTO user = new UserDTO(username, fullname, "", "", "", "", roleID);
                    session.setAttribute("USER", user);
                    url = USER_SUCCESS;
                }
            }
            
        } catch (Exception e) {
            log("SigninController error at: " + e.toString());
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
