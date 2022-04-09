/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vapeshop.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class UserDAO {
    
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
    
    public UserDTO checkLogin (String username, String password) throws SQLException, ClassNotFoundException {
        UserDTO dto = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "SELECT roleID, fullname "
                        + " FROM Users WHERE username = ? AND password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                
                if (rs.next()) {
                    dto = new UserDTO(username, rs.getString("fullname"), ""
                            , "****", "", "", rs.getString("roleID"));
                }
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public boolean creatAccount(UserDTO user) throws ClassNotFoundException, SQLException {
        boolean check = false;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                String sql = "INSERT INTO Users(username, fullname, email, "
                        + " password, address, phone, roleID)"
                        + " VALUES(?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, user.getUsername());
                stm.setString(2, user.getFullname());
                stm.setString(3, user.getEmail());
                stm.setString(4, user.getPassword());
                stm.setString(5, user.getAddress());
                stm.setString(6, user.getPhone());
                stm.setString(7, user.getRoleID());
                int row = stm.executeUpdate();
                if (row > 0)
                    check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}
