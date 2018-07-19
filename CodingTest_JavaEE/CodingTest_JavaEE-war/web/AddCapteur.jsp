<%@page import="model.Capteur"%>
<%@page import="model.CapteurDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id = -1;//Restera à -1 si l'on ajoute, sinon prend l'id de l'objet que l'on cherche à modifier
    List<Object> params = (List<Object>)(request.getAttribute("params"));
    if(!params.isEmpty()){
        id = (Integer)params.get(0);
        if(id < 1){
            out.println("<p>Ce capteur n'existe pas.</p>");
            return;
        }
    }
    
    String initialName = "";
    String title = "Ajouter";
    String button = "Ajouter";
    
    if(id > 0){
        CapteurDAO cdao = new CapteurDAO();
        Capteur capteur = cdao.find(id);
        if(capteur == null){
            out.println("<p>Ce capteur n'existe pas.</p>");
            return;
        }
        initialName = capteur.getName();
        title = "Modifier";
        button = "Enregistrer";
    }
%>
<h1><% out.println(title); %> un capteur</h1>
<form method="POST" action="saveCapteur">
    <input type="hidden" name="id" value=<% out.println(id);%> />
    <label>Nom du capteur</label> : <input type="text" name="name" maxlength="255" value="<% out.println(initialName);%>"/><br/>
    <input type="submit" value="<% out.println(button); %>" />
</form>