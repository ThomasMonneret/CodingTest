<%@page import="model.Produit"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.ProduitDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<a href="accueil">Retourner à l'accueil</a>
<h1>Liste des produits</h1>
<a href="addProduit">Ajouter un produit</a>
<%
    ProduitDAO pdao = new ProduitDAO();
    ArrayList<Produit> produits = pdao.getAllProduits();
    if(produits.isEmpty()){
        out.println("<p>Aucun produit trouvé</p>");
    }
    else{
        out.println("<table>"
                + "<tr>"
                + "<th>Id</th>"
                + "<th>Nom du produit</th>"
                + "<th>Quantité</th>"
                + "<th></th>"
                + "<th></th>"
                + "</tr>");
        for(Produit produit : produits){
            out.println("<tr>"
                    + "<td>" + produit.getId() + "</td>"
                    + "<td>" + produit.getName() + "</td>"
                    + "<td>" + produit.getQuantity() + "</td>"
                    + "<td><a href=\"editProduit?produitId="+produit.getId()+"\"> Modifier</td>"
                    + "<td><a href=\"deleteProduit?produitId="+produit.getId()+"\"> Supprimer</td>"
                    + "</tr>");
        }
        out.println("</table>");
    }
%>