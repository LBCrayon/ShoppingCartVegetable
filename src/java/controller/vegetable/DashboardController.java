    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vegetable;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vapeshop.vegetable.VegetableDAO;
import vapeshop.vegetable.VegetableDTO;
import vapeshop.category.CategoryDAO;
import vapeshop.category.CategoryDTO;

/**
 *
 * @author Admin
 */
public class DashboardController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "dashboard.jsp";

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
        
        try {
            VegetableDAO vegetable = new VegetableDAO();
            List<VegetableDTO> vegetablelist = vegetable.getAllAvailableVegetable();
            CategoryDAO cate = new CategoryDAO();
            List<CategoryDTO> catelist = cate.getAllCategory();

            if (vegetablelist.isEmpty()) {
                request.setAttribute("CAR_LIST", null);
                request.setAttribute("NOTIFY", "There is no vegetable available in the shop right now");
                url = SUCCESS;
            } else {
                request.setAttribute("CAR_LIST", vegetablelist);
                request.setAttribute("CATEGORY", catelist);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("DashboardController error at: " + e.toString());
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
