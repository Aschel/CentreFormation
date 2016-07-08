<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<style>.bouton{border-style:double;padding:10px 30px 10px 30px}</style>
<c:if test="${sessionScope['user'] == null}">
    <h2>Authentification</h2>
</c:if>
<form action="connection" method="POST">
    <table style="width:100%">
        <tr>
            <c:if test="${sessionScope['user'] == null}">
                <td align='Right'>
                    Login <input type="text" name="login" value="${param['login']}">
                    Password <input type="password" name="password" value="${param['password']}" >
                    <button name="action" value="connecter" type="submit">Connection</button><br>
                    <p style="color: red"> ${erreurconnection} </p>
                    <p style="color: red"> ${erreurlogin} </p>
                    <p style="color: red"> ${erreurpassword} </p>
                    <p style="color: green">${okconnection}</p>

                </c:if>
                <c:if test="${sessionScope['user'] != null}">
                <td><a class="bouton" href="projets">Mes projets</a></td>
                <td><a class="bouton" href="creerprojet">Créer un projet</a></td>
                <td><a class="bouton" href="promos">Mes promotions</a></td>
                <td>Bienvenue ${sessionScope['user'].getNom()} ${sessionScope['user'].getPrenom()} <br/>
                    <button name="action" value="deconnecter" type="submit">Se déconnecter</button></td>

            </c:if> 
            </td>
        </tr>
    </table>
</form>
<hr/>