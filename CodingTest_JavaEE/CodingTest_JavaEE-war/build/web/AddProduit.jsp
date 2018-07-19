<%@page import="model.Produit"%>
<%@page import="model.ProduitDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id = -1;//Restera à -1 si l'on ajoute, sinon prend l'id de l'objet que l'on cherche à modifier
    List<Object> params = (List<Object>)(request.getAttribute("params"));
    if(!params.isEmpty()){
        id = (Integer)params.get(0);
        if(id < 1){
            out.println("<p>Ce produit n'existe pas.</p>");
            return;
        }
    }
    
    String initialName = "";
    int initialQuantity = 0;
    String title = "Ajouter";
    String button = "Ajouter";
    
    if(id > 0){
        ProduitDAO pdao = new ProduitDAO();
        Produit produit = pdao.find(id);
        if(produit == null){
            out.println("<p>Ce produit n'existe pas.</p>");
            return;
        }
        initialName = produit.getName();
        initialQuantity = produit.getQuantity();
        title = "Modifier";
        button = "Enregistrer";
    }
%>
<h1><% out.println(title); %> un produit</h1>
<form method="POST" action="saveProduit">
    <input type="hidden" name="id" value=<% out.println(id);%> />
    <label>Nom du produit</label> : <input type="text" name="name" maxlength="255" value="<% out.println(initialName);%>"/><br/>
    <label>Quantité du produit</label> : <input type="number" name="quantity" value=<% out.println(initialQuantity);%> min="0"/><br/>
    <input type="submit" value="<% out.println(button); %>" />
</form>