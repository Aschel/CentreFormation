<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<h2>Authentification</h2>
<form action="connection" method="POST">
    <table>
        <tr>
            <td><button style="font-size: 150%" name="projets" value="projets">Mes projets</button></td>
            <td><button style="font-size: 150%" name="creerProjet" value="creerProjet">Créer un projet</button></td>
            <td><button style="font-size: 150%" name="promos" value="promos">Mes promotions</button></td>
            <td>
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
                    Bienvenue ${sessionScope['user'].getLogin()}
                    <button name="action" value="deconnecter" type="submit">Se déconnecter</button>
                </c:if> 
            </td>
        </tr>

    </table>

</form>
<hr/>