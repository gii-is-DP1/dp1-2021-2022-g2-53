<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">

<p>PASA ESTE CODIGO A TU AMIGO Y COMIENZA A JUGAR CON EL</p>
<h2> "${game.token}"</h2>  
            
            <h2>JUEGA CON TU AMIGO</h2>
       		<spring:url value="/games/saveFriend/{token}"
                            var="gameUrl">
                            <spring:param name="token" value="${game.token}" />
                        </spring:url> <a href="${fn:escapeXml(gameUrl)}">Crear partida</a></td>
    <h2> ${message2}</h2>
    
</petclinic:layout>