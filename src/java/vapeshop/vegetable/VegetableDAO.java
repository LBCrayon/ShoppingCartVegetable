/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vapeshop.vegetable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class VegetableDAO {

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

    public List<VegetableDTO> getAllAvailableVegetable() 
            throws ClassNotFoundException, SQLException {
        List<VegetableDTO> list = new ArrayList();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT vegetableID, vegetableName, quanity, price, sale, "
                        + " categoryID, status FROM Vegetable";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    if (status) {
                        String vegetableID = rs.getString("vegetableID");
                        String vegetableName = rs.getString("vegetableName");
                        int quanity = rs.getInt("quanity");
                        double price = rs.getDouble("price");
                        double sale = rs.getDouble("sale");
                        String categoryID = rs.getString("categoryID");
                        list.add(new VegetableDTO(vegetableID, vegetableName, quanity, price, sale,
                                categoryID, status));
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<VegetableDTO> searchVegetable(String search) 
            throws ClassNotFoundException, SQLException {
        List<VegetableDTO> list = new ArrayList();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT vegetableID, vegetableName, quanity, price, sale, "
                        + " categoryID, status FROM Vegetable "
                        + " WHERE vegetableName LIKE ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    boolean status = rs.getBoolean("status");
                    if (status) {
                        String vegetableID = rs.getString("vegetableID");
                        String vegetableName = rs.getString("vegetableName");
                        int quanity = rs.getInt("quanity");
                        double price = rs.getDouble("price");
                        double sale = rs.getDouble("sale");
                        String categoryID = rs.getString("categoryID");
                        list.add(new VegetableDTO(vegetableID, vegetableName, quanity, price, sale,
                                categoryID, status));
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

//    public List<CarDTO> getAllCar() 
//            throws ClassNotFoundException, SQLException {
//        List<CarDTO> list = new ArrayList();
//        try {
//            con = DBUtils.getConnection();
//            if (con != null) {
//                String sql = "SELECT carID, carName, quantity, price, sale, "
//                        + " categoryID, status FROM Car";
//                stm = con.prepareStatement(sql);
//                rs = stm.executeQuery();
//                while (rs.next()) {
//                    String carID = rs.getString("carID");
//                    String carName = rs.getString("carName");
//                    int quantity = rs.getInt("quantity");
//                    double price = rs.getDouble("price");
//                    double sale = rs.getDouble("sale");
//                    String categoryID = rs.getString("categoryID");
//                    boolean status = rs.getBoolean("status");
//                    list.add(new CarDTO(carID, carName, quantity, price, sale,
//                            categoryID, status));
//                }
//            }
//        } finally {
//            closeConnection();
//        }
//        return list;
//    }
    
    public List<VegetableDTO> adminSearchVegetable(String search, boolean status) 
            throws ClassNotFoundException, SQLException {
        List<VegetableDTO> list = new ArrayList();
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT vegetableID, vegetableName, quanity, price, sale, "
                        + " categoryID, status FROM Vegetable "
                        + " WHERE vegetableName LIKE ? AND status=?";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                stm.setBoolean(2, status);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String vegetableID = rs.getString("vegetableID");
                    String vegetableName = rs.getString("vegetableName");
                    int quanity = rs.getInt("quanity");
                    double price = rs.getDouble("price");
                    double sale = rs.getDouble("sale");
                    String categoryID = rs.getString("categoryID");
                    list.add(new VegetableDTO(vegetableID, vegetableName, quanity, price, sale,
                            categoryID, status));
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
    
    public boolean Delete(String vegetableID) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE Vegetable SET status = 'false' "
                        + " WHERE vegetableID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, vegetableID);
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
    
    public boolean Launch(String vegetableID) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE Vegetable SET status = 'true' "
                        + " WHERE vegetableID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, vegetableID);
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
    
    public boolean Update(VegetableDTO car) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "UPDATE Vegetable SET vegetableName=?, quanity=?, price=?, sale=? "
                        + " WHERE vegetableID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, car.getVegetableName());
                stm.setInt(2, car.getQuanity());
                stm.setDouble(3, car.getPrice());
                stm.setDouble(4, car.getSale());
                stm.setString(5, car.getCategoryID());
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
}
