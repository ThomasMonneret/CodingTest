/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Capteur;
import model.CapteurDAO;
import model.Produit;
import model.ProduitDAO;

public class ServletRedirector extends HttpServlet{
    
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String relativeRessource = request.getRequestURI().substring(request.getContextPath().length());//Renvoie la ressource relative demandée (par exemple : /capteurs)
        String servlet = "Notfound.jsp";
        String title = "Erreur";
        List<Object> params = new ArrayList<>();
        
        if(relativeRessource.equals("/") || relativeRessource.equals("/accueil")){
            servlet = "Accueil.jsp";
            title = "Accueil";
        }
        else if(relativeRessource.equals("/capteurs")){
            servlet = "ListeCapteurs.jsp";
            title = "Capteurs";
        }
        else if(relativeRessource.equals("/addCapteur")){
            servlet = "AddCapteur.jsp";
            title = "Ajouter un capteur";
        }
        else if(relativeRessource.equals("/editCapteur") && request.getParameter("capteurId") != null){
            servlet = "AddCapteur.jsp";
            title = "Modifier un capteur";
            params.add(Integer.parseInt(request.getParameter("capteurId")));
        }
        else if(relativeRessource.equals("/deleteCapteur") && request.getParameter("capteurId") != null){
            servlet = "DeleteCapteur.jsp";
            title = "Supprimer un capteur";
            params.add(Integer.parseInt(request.getParameter("capteurId")));
        }
        else if(relativeRessource.equals("/produits")){
            servlet = "ListeProduits.jsp";
            title = "Produits";
        }
        else if(relativeRessource.equals("/addProduit")){
            servlet = "AddProduit.jsp";
            title = "Ajouter un produit";
        }
        else if(relativeRessource.equals("/editProduit") && request.getParameter("produitId") != null){
            servlet = "AddProduit.jsp";
            title = "Modifier un produit";
            params.add(Integer.parseInt(request.getParameter("produitId")));
        }
        else if(relativeRessource.equals("/deleteProduit") && request.getParameter("produitId") != null){
            servlet = "DeleteProduit.jsp";
            title = "Supprimer un produit";
            params.add(Integer.parseInt(request.getParameter("produitId")));
        }

        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("content", servlet);
        request.setAttribute("title", title);
        request.setAttribute("params", params);
        RequestDispatcher rd = request.getRequestDispatcher("Layout.jsp");
        rd.forward(request, response);//response.sendRedirect("Layout.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String relativeRessource = request.getRequestURI().substring(request.getContextPath().length());//Renvoie la ressource relative demandée (par exemple : /capteurs)
        String servlet = "Notfound.jsp";
        String title = "Erreur";
        
        if(relativeRessource.equals("/saveCapteur")){
            CapteurDAO cdao = new CapteurDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            Capteur capteur = new Capteur(id, request.getParameter("name"));
                
            if(id > 0){//Si le capteur doit être modifié
                if(cdao.update(capteur)){
                    title = "Modifier un capteur";
                    servlet = "CapteurEdited.jsp";
                }
                else{
                    servlet = "DatabaseError.jsp";
                }
            }
            else{//Si le capteur doit être ajouté
                if(cdao.create(capteur)){
                    title = "Ajouter un capteur";
                    servlet = "CapteurAdded.jsp";
                }
                else{
                    servlet = "DatabaseError.jsp";
                }
            }
        }
        else if(relativeRessource.equals("/deleteCapteur")){
            CapteurDAO cdao = new CapteurDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            Capteur capteur = new Capteur(id, "");//Le nom importe peu
                
            if(cdao.delete(capteur)){
                title = "Supprimer un capteur";
                servlet = "CapteurDeleted.jsp";
            }
            else{
                servlet = "DatabaseError.jsp";
            }
        }
        else if(relativeRessource.equals("/saveProduit")){
            ProduitDAO pdao = new ProduitDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            int quantity;
            if(request.getParameter("quantity").equals("")){//Si le champ quantité a été laissé vide
                quantity = -1;//Sera interprété comme null, la quantité minimale étant 0
            }
            else{
                quantity = Integer.parseInt(request.getParameter("quantity"));
            }
            
            Produit produit = new Produit(id, request.getParameter("name"), quantity);
                
            if(id > 0){//Si le produit doit être modifié
                if(pdao.update(produit)){
                    title = "Modifier un produit";
                    servlet = "ProduitEdited.jsp";
                }
                else{
                    servlet = "DatabaseError.jsp";
                }
            }
            else{//Si le produit doit être ajouté
                if(pdao.create(produit)){
                    title = "Ajouter un produit";
                    servlet = "ProduitAdded.jsp";
                }
                else{
                    servlet = "DatabaseError.jsp";
                }
            }
        }
        else if(relativeRessource.equals("/deleteProduit")){
            ProduitDAO pdao = new ProduitDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            Produit produit = new Produit(id, "", 0);//Le nom et la quantité importent peu
                
            if(pdao.delete(produit)){
                title = "Supprimer un produit";
                servlet = "ProduitDeleted.jsp";
            }
            else{
                servlet = "DatabaseError.jsp";
            }
        }
        
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("content", servlet);
        request.setAttribute("title", title);
        RequestDispatcher rd = request.getRequestDispatcher("Layout.jsp");
        rd.forward(request, response);//response.sendRedirect("Layout.jsp");
    }
}
