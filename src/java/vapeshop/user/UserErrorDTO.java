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
public class UserErrorDTO {
    private String usernameError;
    private String fullnameError;
    private String emailError;
    private String addressError;
    private String phoneError;
    private String passwordError;
    private String confirmError;

    public UserErrorDTO() {
    }

    public UserErrorDTO(String usernameError, String fullnameError, String emailError, String addressError, String phoneError, String passwordError, String confirmError) {
        this.usernameError = usernameError;
        this.fullnameError = fullnameError;
        this.emailError = emailError;
        this.addressError = addressError;
        this.phoneError = phoneError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getFullnameError() {
        return fullnameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public String getAddressError() {
        return addressError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setFullnameError(String fullnameError) {
        this.fullnameError = fullnameError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }
    
}
