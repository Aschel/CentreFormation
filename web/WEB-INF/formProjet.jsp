<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Création d'un projet</title>
    </head>
    <body>   
        <p:header/>
        <form method="post">

            Promotion : <input type="text" name="promotion" value="${param['promotion']}">
            <br>
            <br>


            Sujet : <input type="number" name="sujet" value="${param['sujet']}">
            <br>
            <br>

            Titre : <input type="text" name="titre" value="${param['titre']}">
            <br>
            <br>

            Date Limite : <input type="text" name="dateLimite" value="${param['dateLimite']}">
            <br>
            <br>


            <div class="button">
                <button type="submit" id="prix">Créer projet</button>
            </div>

        </form>
    </body>
</html>
