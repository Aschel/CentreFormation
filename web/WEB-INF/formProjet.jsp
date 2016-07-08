<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Création d'un projet</title>
        <style>.error{color:red;}</style>
    </head>
    <body>   
        <p:header/>
        <form method="post">

            Promotion : <input type="number" name="promotion" value="${param['promotion']}">
            <span class="error">${erreurPromo}
                ${erreurPromo2}</span>
            <br>
            <br>
            Titre : <input type="text" name="titre" value="${param['titre']}">
            <span class="error">${erreurTitre}</span>
            <br>
            <br>
            Sujet : <input type="text" name="sujet" value="${param['sujet']}">
            <span class="error">${erreurSujet}</span>
            <br>
            <br>
            Date Limite : <input type="date" name="dateLimite" value="${param['dateLimite']}">
            <span class="error">${erreurDate}</span>
            <br>
            <br>


            <div class="button">
                <button type="submit" id="projet">Créer projet</button>
            </div>

        </form>
    </body>
</html>
