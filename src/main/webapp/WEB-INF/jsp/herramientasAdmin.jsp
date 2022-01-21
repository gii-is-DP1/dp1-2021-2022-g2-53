<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  
<body style= "background: url(/resources/images/backgroundPetris.jpg)">
<petclinic:layout pageName="tools">

  <div class="row" >
	<div class="col-md-2">
            
        </div>
	<div class="col-md-8">
            <spring:url value="/resources/images/PetrisLogo.png" htmlEscape="true" var="PetrisLogo"/>
            <img class="img-responsive" src="${PetrisLogo}" width="800px" />
        </div>
     <div class="col-md-2">
       
        </div>
  </div>
  <br></br>
  <div class="row">
   <h2>Bienvenido a las herramientas de Administrador de Petris</h2>

         
</div>
     
     <div class="col-md-12" >
     
     
       <div class = row>
     <br></br>
      
		<p>
		
		<button class="btn btn-default" onclick="window.location.href='/games/mostrarpartidas'">Listado de partidas jugadas y sus participantes</button>
		
		<br></br><br><br>
		
		</p>
		
        </div>
      
          <div class= row >
     <br></br>
      
		<p>
		
		<button class="btn btn-default" onclick="window.location.href='/personas/registro'">Usuarios registrados</button>
		<h2></h2>
		<br></br><br>
		
		
		<p>
        </div>
        
		 <div class="col-md-8">
		 <h2> ${group}</h2>
           <ul>
			<c:forEach items="${personas}" var="person">
				<li>${person.firstName}&nbsp;${person.lastName}</li>
			</c:forEach>
		</ul>
        </div>
    <div class="col-md-4">
            <spring:url value="/resources/images/logoUniversidad_3.png" htmlEscape="true" var="logoUniImage"/>
            <img class="img-responsive" src="${logoUniImage}"/>
        </div>
    </div>
    
</petclinic:layout>
</body>