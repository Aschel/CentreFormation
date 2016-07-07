<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<h2>Authentification</h2>
<form action="connection" method="POST">
    <div>
        <button name="projets" value="projets">Mes projets</button>
    </div>
    <c:if test="${sessionScope['user'] == null}">
        Login <input type="text" name="login" value="${param['login']}">
        Password <input type="password" name="password" value="${param['password']}" >
        <button name="action" value="connecter" type="submit">Connection</button><br>
        <p style="color: red"> ${erreurconnection} </p>
        <p style="color: red"> ${erreurlogin} </p>
        <p style="color: red"> ${erreurpassword} </p>
        <p style="color: green">${okconnection}</p>

    </c:if>
    <c:if test="${sessionScope['user'] != null}">
        Connecter en tant que ${sessionScope['user'].getLogin()}
        <button name="action" value="deconnecter" type="submit">Se déconnecter</button>
    </c:if>
</form>
<hr/>