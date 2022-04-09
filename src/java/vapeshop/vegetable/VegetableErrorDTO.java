/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vapeshop.vegetable;

/**
 *
 * @author Admin
 */
public class VegetableErrorDTO {
    private String errorName;
    private String errorQuantity;

    public VegetableErrorDTO() {
    }

    public VegetableErrorDTO(String errorName, String errorQuantity) {
        this.errorName = errorName;
        this.errorQuantity = errorQuantity;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getErrorQuantity() {
        return errorQuantity;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public void setErrorQuantity(String errorQuantity) {
        this.errorQuantity = errorQuantity;
    }
    
}
