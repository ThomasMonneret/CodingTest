<%@page import="model.Capteur"%>
<%@page import="model.CapteurDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    int id = -1;//Restera à -1 si aucun capteur ne correspond
    List<Object> params = (List<Object>)(request.getAttribute("params"));
    if(!params.isEmpty()){
        id = (Integer)params.get(0);
    }
    
    if(id < 1){
        out.println("<p>Ce capteur n'existe pas.</p>");
        return;
    }
    else{
        CapteurDAO cdao = new CapteurDAO();
        Capteur capteur = cdao.find(id);
        if(capteur == null){
            out.println("<p>Ce capteur n'existe pas.</p>");
            return;
        }
    }
%>
<h1>Supprimer un capteur</h1>
<p>Voulez-vous vraiment supprimer ce capteur ? Cette action est irréversible !</p>
<form method="POST" action="deleteCapteur">
    <input type="hidden" name="id" value=<% out.println(id);%> />
    <input type="submit" value="Supprimer le capteur" />
</form>