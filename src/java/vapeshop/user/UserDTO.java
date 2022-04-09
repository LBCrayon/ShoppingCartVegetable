/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vapeshop.user;

/**
 *
 * @author Admin
 */
public class UserDTO {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String roleID;

    public UserDTO(String username, String fullname, String email, String password, String address, String phone, String roleID) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.roleID = roleID;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }
    
}
