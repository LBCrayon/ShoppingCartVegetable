/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vegetable;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vapeshop.vegetable.VegetableDAO;
import vapeshop.vegetable.VegetableDTO;
import vapeshop.vegetable.VegetableErrorDTO;
import vapeshop.user.UserDTO;

/**
 *
 * @author Admin
 */
public class UpdateController extends HttpServlet {
    private static final String ERROR = "dashboard.jsp";
    private static final String SUCCESS = "SearchController";
    private static final String IS_USER = "home.jsp";

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
            String vegetableID = request.getParameter("vegetableID");
            String vegetableName = request.getParameter("vegetableName");
            String tmpQuantity = request.getParameter("quanity");
            double price = Double.parseDouble(request.getParameter("price"));
            double sale = Double.parseDouble(request.getParameter("sale"));
            String search = request.getParameter("search");
            boolean status = Boolean.parseBoolean(request.getParameter("status"));
            boolean fault = false;
            VegetableErrorDTO error = new VegetableErrorDTO("", "");
            
            if (vegetableName.length() < 3 || vegetableName.length() > 20) {
                error.setErrorName("The car name must be 3 to 20 character");
                fault = true;
            }
            
            if (tmpQuantity.contains(".")) {
                error.setErrorQuantity("Quantity must be integer number");
                fault = true;
            }
            
            if (user.getRoleID().equals("AD002")) {
            if (fault){
                request.setAttribute("UPDATE_ERROR", error);
            } else {
                int quanity = Integer.parseInt(tmpQuantity);
                VegetableDTO car = new VegetableDTO(vegetableID, vegetableName, quanity, price, sale, vegetableID, status);
                VegetableDAO dao = new VegetableDAO();
                boolean check = dao.Update(car);
                if (!check) {
                    request.setAttribute("ERROR", "Unable to update this car because of some porblem");
                }
            }
            if (search == null) {
                search="";
            }
            url = SUCCESS+"?search="+search+"&status="+status;
            } else {
                url = IS_USER;
            }
        } catch (Exception e) {
            log("UpdateController error at: " +e.toString());
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
