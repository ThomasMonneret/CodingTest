<%@page import="java.util.ArrayList"%>
<%@page import="model.Capteur"%>
<%@page import="model.CapteurDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<a href="accueil">Retourner à l'accueil</a>
<h1>Liste des capteurs</h1>
<a href="addCapteur">Ajouter un capteur</a>
<%
    CapteurDAO cdao = new CapteurDAO();
    ArrayList<Capteur> capteurs = cdao.getAllCapteurs();
    if(capteurs.isEmpty()){
        out.println("<p>Aucun capteur trouvé</p>");
    }
    else{
        out.println("<table>"
                + "<tr>"
                + "<th>Id</th>"
                + "<th>Nom du capteur</th>"
                + "<th></th>"
                + "<th></th>"
                + "</tr>");
        for(Capteur capteur : capteurs){
            out.println("<tr>"
                    + "<td>" + capteur.getId() + "</td>"
                    + "<td>" + capteur.getName() + "</td>"
                    + "<td><a href=\"editCapteur?capteurId="+capteur.getId()+"\"> Modifier</td>"
                    + "<td><a href=\"deleteCapteur?capteurId="+capteur.getId()+"\"> Supprimer</td>"
                    + "</tr>");
        }
        out.println("</table>");
    }
%>
    