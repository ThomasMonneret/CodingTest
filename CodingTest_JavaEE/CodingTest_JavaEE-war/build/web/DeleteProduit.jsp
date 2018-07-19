<%@page import="model.Produit"%>
<%@page import="model.ProduitDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id = -1;//Restera à -1 si aucun produit ne correspond
    List<Object> params = (List<Object>)(request.getAttribute("params"));
    if(!params.isEmpty()){
        id = (Integer)params.get(0);
    }
    
    if(id < 1){
        out.println("<p>Ce produit n'existe pas.</p>");
        return;
    }
    else{
        ProduitDAO pdao = new ProduitDAO();
        Produit produit = pdao.find(id);
        if(produit == null){
            out.println("<p>Ce produit n'existe pas.</p>");
            return;
        }
    }
%>
<h1>Supprimer un produit</h1>
<p>Voulez-vous vraiment supprimer ce produit ? Cette action est irréversible !</p>
<form method="POST" action="deleteProduit">
    <input type="hidden" name="id" value=<% out.println(id);%> />
    <input type="submit" value="Supprimer le produit" />
</form>