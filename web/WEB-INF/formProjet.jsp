<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Création d'un projet</title>
    </head>
    <body>   

        <form method="post">

            Promotion : <input type="text" name="nom" value="${param['promotion']}">
            <br>
            <br>


            Sujet : <input type="number" name="prix" value="${param['sujet']}">
            <br>
            <br>

            Titre : <input type="text" name="nom" value="${param['titre']}">
            <br>
            <br>

            Date Limite : <input type="text" name="nom" value="${param['dateLimite']}">
            <br>
            <br>


            <div class="button">
                <button type="submit" id="prix">Créer projet</button>
            </div>

        </form>
    </body>
</html>
