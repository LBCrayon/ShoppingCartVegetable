/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vapeshop.cart;

import java.util.HashMap;
import java.util.Map;
import vapeshop.vegetable.VegetableDTO;

/**
 *
 * @author Admin
 */
public class CartDTO {

    private Map<String, VegetableDTO> cart;

    public CartDTO() {
        cart = new HashMap<>();
    }

    public CartDTO(Map<String, VegetableDTO> cart) {
        this.cart = cart;
    }

    public Map<String, VegetableDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, VegetableDTO> cart) {
        this.cart = cart;
    }

    public boolean add(VegetableDTO vegetable) {
        boolean check = false;
        if (this.cart == null) {
            this.cart = new HashMap();
        }
        if (this.cart.containsKey(vegetable.getCategoryID())) {
            int quantity = this.cart.get(vegetable.getVegetableID()).getQuanity();
            vegetable.setQuanity(quantity + vegetable.getQuanity());
        }
        cart.put(vegetable.getVegetableID(), vegetable);
        check = true;
        return check;
    }

    public boolean delete(String vegetableID) {
        boolean check = false;
        if (this.cart == null) {
            check = false;
        }
        if (this.cart.containsKey(vegetableID)) {
            this.cart.remove(vegetableID);
            check = true;
        }
        return check;
    }

    public boolean update(String vegetableID, VegetableDTO vegetable) {
        boolean check = false;
        if (this.cart == null) {
            check = false;
        }
        if (this.cart.containsKey(vegetableID)) {
            this.cart.replace(vegetableID, vegetable);
            check = true;
        }
        return check;
    }
}
