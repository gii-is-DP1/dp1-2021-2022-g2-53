<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">
<body style= "background: url(/resources/images/backgroundPetris.jpg)">

<p align=center>PASA ESTE CÓDIGO A TU AMIGO Y COMIENZA A JUGAR CON ÉL</p>
<br>
<h1 align=center> "${game.token}"</h1>  
            
            <h2 align=center>JUEGA CON TU AMIGO</h2>
<br> 


<div class="row" >
	<div class="col-md-4">
            
        </div>
	<div class="col-md-4" >
      
		<p>
		<spring:url value="/resources/images/img23 (1).jpg" htmlEscape="true" var="error2"/>
            <img id="f4" class="img-responsive" style=" border: 2px solid; border-radius:15px;color: #E8CEF5;" src="${error2}"/>
        <br> 
            
        <br>
        
            <button class="btn btn-default"  style = "margin-left:31%" onclick="window.location.href='/games/saveFriend/${game.token}' "><span>Crear Partida</span></button>
		
        </div>
     <div class="col-md-2">
       
        </div>
  </div>            
       		
    <h2> ${message2}</h2>
    
</petclinic:layout>