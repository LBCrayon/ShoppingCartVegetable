/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vapeshop.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import vapeshop.vegetable.VegetableDTO;
import vapeshop.cart.CartDTO;
import vapeshop.user.UserDTO;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class OrderDAO {

    private Connection con = null;
    private PreparedStatement stm = null;
    private ResultSet rs = null;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean CreateOrder(UserDTO user, double total) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO Orders(username, total) "
                        + " VALUES(?,?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getUsername());
                stm.setDouble(2, total);
                int row = stm.executeUpdate();
                if (row > 0) {
                    check = true;
                }
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public int GetNewestOrderID() throws ClassNotFoundException, SQLException {
        int orderID = 0;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT TOP 1 orderID FROM Orders "
                        + " ORDER BY orderDate DESC";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                if (rs.next()) {
                    orderID = rs.getInt("orderID");
                }
            }
        } finally {
            closeConnection();
        }
        return orderID;
    }

    public boolean CreateOrderDetails(int orderID, CartDTO cart) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                for (VegetableDTO car : cart.getCart().values()) {
                    String sql = "INSERT INTO OrderDetails(orderID, vegetableID, "
                            + " quantity, salePrice, totalPrice) "
                            + " VALUES(?,?,?,?,?) ";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, orderID);
                    stm.setString(2, car.getVegetableID());
                    stm.setInt(3, car.getQuanity());
                    double salePrice = car.getPrice()*car.getQuanity()*
                            (car.getSale()/100);
                    stm.setDouble(4, salePrice);
                    double total = car.getPrice()*car.getQuanity() - salePrice;
                    stm.setDouble(5, total);
                    int row = stm.executeUpdate();
                    if (row > 0) {
                        check = true;
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return check;
    }

}
