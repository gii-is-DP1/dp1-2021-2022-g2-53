<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="personas">
    <h2>Personas</h2>


    <table id="gamesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 160px;">username</th>
            <th style="width: 160px;">nombre</th>
            <th style="width: 80px;">apellidos</th>
            <th style="width: 80px;"></th>
            <th style="width: 80px;"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${personas}" var="personas">
            <tr>
                <td>
                   
                    <c:out value="${personas.user.username}"/>
                </td>
                <td>
                   
                    <c:out value="${personas.firstName}"/>
                </td>
                 <td>
                   
                    <c:out value="${personas.lastName}"/>
                </td>
               
                             
            
               <td><spring:url value="/personas/edit/{personaId}"
                            var="personaUrl2">
                            <spring:param name="gameId" value="${personas.id}" />
                        </spring:url> <a href="${fn:escapeXml(personaUrl2)}">Editar</a></td>
                        
                        
                <td><spring:url value="/personas/delete/{personaId}" var="gameUrl3">
                            <spring:param name="personaId" value="${personas.id}" />
                        </spring:url> <a href="${fn:escapeXml(personaUrl3)}">Borrar</a></td>
                             
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
