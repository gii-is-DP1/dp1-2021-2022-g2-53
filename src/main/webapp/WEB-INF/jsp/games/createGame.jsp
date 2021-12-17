<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">

    <form:form modelAttribute="game" class="form-horizontal"  action="/games/save">
         <div class="form-group has-feedback">
            
      

        </div>
        <div class="form-group">

        </div>
    </form:form>
    <h2>CREA LA PARTIDA</h2>
    
    <spring:url value="/games/newGame"
                            var="gameUrl2">
    </spring:url> <a href="${fn:escapeXml(gameUrl2)}">Juega con amigos</a></td>
               
                <h2>ÚNETE A TU AMIGO</h2>
                
       		<spring:url value="/games/join"
                            var="gameUrl">
                            <spring:param name="token" value="${game.token}" />
                        </spring:url> <a href="${fn:escapeXml(gameUrl)}">Únete a tu amigo</a></td>
                        
                        
    
</petclinic:layout>