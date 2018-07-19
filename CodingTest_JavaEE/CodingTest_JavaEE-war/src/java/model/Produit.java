/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

public class Produit {
    private int id;
    private String name;
    private int quantity;
    
    public Produit(int id, String name, int quantity){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
