<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <p:header/>    
        
        <form method="post">
            Mes projets : <input type="text" name="nom" value="${param['nom']}">
            <br>
            <br>

            Prix : <input type="number" name="prix" value="${param['prix']}">
            <c:if test="${erreurPrix != null}">
                <span>${erreurPrix}</span>    
            </c:if>
            <br>
            <br> 
            <div class="button">
                <button type="submit" id="prix">Ajouter</button>
            </div>
            ${message}
        </form>
    </body>
</html>
