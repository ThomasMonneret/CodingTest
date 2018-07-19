/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProduitDAO extends DAO<Produit>{
    @Override
    public boolean create(Produit obj){
        try {
            BDManager.executeUpdateQuery("INSERT INTO Produit (name, quantity) VALUES ('" + obj.getName() + "', " + (obj.getQuantity() >= 0 ? obj.getQuantity() : "null") + ")");
        } catch (Exception ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean update(Produit obj){
        try {
            BDManager.executeUpdateQuery("UPDATE Produit SET name = '" + obj.getName() + "', quantity = " + (obj.getQuantity() >= 0 ? obj.getQuantity() : "null") + " WHERE id = " + obj.getId());
        } catch (Exception ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean delete(Produit obj){
        try {
            BDManager.executeUpdateQuery("DELETE FROM Produit WHERE id = " + obj.getId());
        } catch (Exception ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    @Override
    public Produit find(int id){
        Produit p = null;
        try {
            ResultSet rset = BDManager.executeSelectQuery("SELECT * FROM Produit WHERE id = " + id);
            if(rset.next()){
                p = new Produit(rset.getInt("id"), rset.getString("name"), rset.getInt("quantity"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
    }
    
    public ArrayList<Produit> getAllProduits() throws SQLException, ClassNotFoundException, IOException{
        ArrayList<Produit> produits = new ArrayList<>();
        ResultSet rset = BDManager.executeSelectQuery("SELECT * FROM Produit ORDER BY id");
        
        while(rset.next()){
            produits.add(new Produit(rset.getInt("id"), rset.getString("name"), rset.getInt("quantity")));
        }
        
        return produits;
    }
}
