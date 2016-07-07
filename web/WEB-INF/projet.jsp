<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projet</title>
    </head>
    <body>
        <h1>Projet ${projet.id}</h1>
        <c:if test="${projet == null}">
            Pas de projet avec cet id
        </c:if>
        <c:if test="${projet != null}">
            Projet ${projet.getId}
        </c:if>
    </body>
</html>
