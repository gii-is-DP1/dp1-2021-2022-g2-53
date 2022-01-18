<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="jugadores">
    <h2>Jugadores</h2>


    <table id="gamesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 160px;">Puntos del jugador negro</th>
            <th style="width: 160px;">Puntos del jugador rojo</th>
            <th style="width: 80px;"></th>
            <th style="width: 80px;"></th>
            <th style="width: 80px;"></th>
        </tr>
        </thead>
        <tbody>
        
        <c:forEach items="${usernames}" var="usernames">
            <tr>
                <td>
                   
                   
                </td>
                <td>
                   
                    <c:out value="${usernames}"/>
                </td>
                
            
              
                        
                        
              
            </tr>
            </c:forEach>
        
        </tbody>
    </table>
</petclinic:layout>
