<%-- 
    Document   : connection
    Created on : 29 juin 2016, 09:41:17
    Author     : Utilisateur
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <center>
        <body>
            <h1>Authentification</h1>
            <form method="POST" action="connection">
                <c:if test="${sessionScope['user'] == null}">
                    Veuillez vous authentifier<br><br>
                    Login <input type="email" name="login" value="${param['login']}"><br>
                    <br>Password <input type="password" name="password" value="${param['password']}" ><br>
                    <br><button name="action" value="connecter" type="submit">Connection</button><br>
                    <p style="color: red"> ${erreurconnection} </p>
                    <p style="color: red"> ${erreurlogin} </p>
                    <p style="color: red"> ${erreurpassword} </p>
                    <p style="color: green">${okconnection}</p>

                </c:if>
                <c:if test="${sessionScope['user'] != null}">
                    <br><button name="action" value="deconnecter" type="submit">Deconnecter ${sessionScope['user'].getLogin()}</button>
                </c:if>
            </form>
        </body> 
    </center>
</html>
