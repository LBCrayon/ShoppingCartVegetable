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
public class VegetableDTO {
    private String vegetableID;
    private String vegetableName;
    private int quanity;
    private double price;
    private double sale;
    private String categoryID;
    private boolean status; 

    public VegetableDTO() {
    }

    public VegetableDTO(String vegetableID, String vegetableName, int quanity, double price, double sale, String categoryID, boolean status) {
        this.vegetableID = vegetableID;
        this.vegetableName = vegetableName;
        this.quanity = quanity;
        this.price = price;
        this.sale = sale;
        this.categoryID = categoryID;
        this.status = status;
    }

    public void setCarID(String vegetableID) {
        this.vegetableID = vegetableID;
    }

    public void setCarName(String vegetableName) {
        this.vegetableName = vegetableName;
    }

    public void setQuanity(int quanity) {
        this.quanity = quanity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getVegetableID() {
        return vegetableID;
    }

    public String getVegetableName() {
        return vegetableName;
    }

    public int getQuanity() {
        return quanity;
    }

    public double getPrice() {
        return price;
    }

    public double getSale() {
        return sale;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public boolean isStatus() {
        return status;
    }

    
}
