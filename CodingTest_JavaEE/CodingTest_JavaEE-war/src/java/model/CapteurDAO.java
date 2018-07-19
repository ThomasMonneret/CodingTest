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

public class CapteurDAO extends DAO<Capteur>{
    @Override
    public boolean create(Capteur obj){
        try {
            BDManager.executeUpdateQuery("INSERT INTO Capteur (name) VALUES ('" + obj.getName() + "')");
        } catch (Exception ex) {
            Logger.getLogger(CapteurDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean update(Capteur obj){
        try {
            BDManager.executeUpdateQuery("UPDATE Capteur SET name = '" + obj.getName() + "' WHERE id = " + obj.getId());
        } catch (Exception ex) {
            Logger.getLogger(CapteurDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean delete(Capteur obj){
        try {
            BDManager.executeUpdateQuery("DELETE FROM Capteur WHERE id = " + obj.getId());
        } catch (Exception ex) {
            Logger.getLogger(CapteurDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    @Override
    public Capteur find(int id){
        Capteur c = null;
        try {
            ResultSet rset = BDManager.executeSelectQuery("SELECT * FROM Capteur WHERE id = " + id);
            if(rset.next()){
                c = new Capteur(rset.getInt("id"), rset.getString("name"));
            }
        } catch (Exception ex) {
            Logger.getLogger(CapteurDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return c;
    }
    
    public ArrayList<Capteur> getAllCapteurs() throws SQLException, ClassNotFoundException, IOException{
        ArrayList<Capteur> capteurs = new ArrayList<>();
        ResultSet rset = BDManager.executeSelectQuery("SELECT * FROM Capteur ORDER BY id");
        
        while(rset.next()){
            capteurs.add(new Capteur(rset.getInt("id"), rset.getString("name")));
        }
        
        return capteurs;
    }
}
