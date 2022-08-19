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
  <div class="row" >
   <h2 align="center">Bienvenido a las herramientas de Administrador de Petris</h2>
    </div>
    <br>
   <div class="row" align="center">
        		<button class="btn btn-default" id = "pedrito2" style="margin-left:-20px" onclick="window.location.href='/users/auditoria'"><span>Registro de modificación</span></button>
   
    </div>  
     
     <div class="col-md-4" style="margin-top:220px;margin-left:-50px">
     
     
       <div class = row>
     <br></br>
      
		<p>
		
		<button class="btn btn-default" id = "pedrito" style="margin-left:150px" onclick="window.location.href='/games/mostrarpartidas'"><span>Listado de partidas jugadas</span></button>
		
		<br></br><br><br>
		
		</p>
		
        </div>
        
		 
    </div>
    <div class="col-md-4" >
    <spring:url value="/resources/images/FotoError.jpg" htmlEscape="true" var="error"/>
            <img id="f2" class="img-responsive" style="border: 2px solid; margin-left:40px; margin-top:40px;border-radius:15px;color: #E8CEF5;" src="${error}"/>
    </div>
    
     <div class="col-md-4" style="margin-top:220px">
    <div class= row >
     <br></br>
      
		<p>
		
		<button class="btn btn-default" id = "pedrito" style="margin-left:80px" onclick="window.location.href='/personas/registro/0'"><span>Usuarios registrados</span></button>
		
		<button class="btn btn-default" id = "pedrito" style="margin-left:80px" onclick="window.location.href='/games/mostrarpartidasencurso'"><span>Listado de partidas en curso</span></button>
		<h2></h2>
		<br></br><br>
		
		
		<p>
		
		<p>
		
		
		
		
		<p>
        </div>
    </div>
    
</petclinic:layout>
</body>