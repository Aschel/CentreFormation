<%-- 
    Document   : index
    Created on : 23 juin 2016, 11:42:39
    Author     : Utilisateur
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Acceuil</title>
    </head>
    <body>
        <h1>Bonjour</h1>
        <img src="img/chat.jpg" alt="le chat"/>
        <p>Do you follow me ?</p>
        <form action="connection" method="post">
            <c:if test="${sessionScope['user'] != null}">
                <br><button name="action" value="deconnecter" type="submit">Deconnecter ${sessionScope['user'].getPrenom()}</button>
            </c:if>
        </form>
    </body>
</html>
