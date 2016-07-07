<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projet</title>
    </head>
    <body>
        <h1>${projet.getTitre()}</h1>
        <br/>Promotion : ${projet.getIdPromotion()}
        <br/>Créé par : ${projet.getIdCreateur()}
        <br/>Sujet : ${projet.getSujet()}
        <br/>Créer le : ${projet.getDateCreation()}
        <br/>Fin le : ${projet.getDateLimite()}
        
    </body>
</html>
