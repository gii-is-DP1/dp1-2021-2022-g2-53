<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<body style= "background: url(/resources/images/bck-petris.png)">
<petclinic:layout pageName="users">

    <h2>Tabla de modificaciones</h2>
      <table id="UsersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 160px;">Nombre de usuario</th>
             <th style="width: 160px;">Contraseña</th>
              <th style="width: 160px;">Modificado por</th>
               <th style="width: 160px;">Fecha de modificacion</th>
               <th style="width: 160px;">Creador</th>
               <th style="width: 160px;">Fecha de creación</th>
        
          
     
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="users" >
        
        
        <c:if test="${users.modifier != null }">
            <tr>
                <td>
                   
                    <c:out value="${users.username}"/>
                </td>
                 <td>
                   
                    <c:out value="${users.password}"/>
                </td>
                 <td>
                   
                    <c:out value="${users.modifier}"/>
                </td>
                 <td>
                   
                    <c:out value="${users.lastModifiedDate}"/>
                </td>
                 <td>
                   
                    <c:out value="${users.creator}"/>
                </td>
                 <td>
                   
                    <c:out value="${users.createdDate}"/>
                </td>
            
                
                   
   
               
            </tr>
             </c:if>
        </c:forEach>
        
        </tbody>
    </table>


 
    
                
</petclinic:layout>
