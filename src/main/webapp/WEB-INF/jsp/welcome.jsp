<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<body style= "background: url(/resources/images/backgroundPetris.jpg)">
<petclinic:layout pageName="home">

  <div class="row" >
	<div class="col-md-2">
            
        </div>
	<div class="col-md-8">
            <!--  spring:url value="/resources/images/PetrisLogo.png" htmlEscape="true" var="PetrisLogo"/>
            <img class="img-responsive" src="${PetrisLogo}" width="800px" />
            -->
        </div>
     <div class="col-md-2">
       
        </div>
  </div>
  <div align = "center" style="margin-left:50px;margin-top:-20px;">
  
  <spring:url value="/resources/images/videoPetris3.gif" htmlEscape="true" var="videoPetris"/>
  <img id="f1" class="img-responsive" style="border: 2px solid; border-radius:15px;color: #E8CEF5;" src="${videoPetris}" width="720" height = "450"/>
  </div>
  <security:authorize access="hasAuthority('persona')">
  <section id="sectionA">
  <br></br>
  
  <div class="row" id="popa">
   <h2>Bienvenido a Petris</h2>

         
</div>
     
     <div class="row"  >
     
     <div class="col-md-4">
     <br>
           
		<p>
		<br>
		
		
		<spring:url value="/resources/images/FotoError.jpg" htmlEscape="true" var="error"/>
            <img id="f2" class="img-responsive" style="border: 2px solid; border-radius:15px;color: #E8CEF5;" src="${error}"/>
		<br>	
		<p>
			<button class="btn btn-default" style = "margin-left:30%" onclick="window.location.href='games/new'"><span>Juega con amigos</span></button>
		</p>

		
			<h2></h2>
			<br></br><br>
		
		
		<p>
        </div>
        
        <div class="col-md-4">
        
        <br></br>
		<p>
		
          <spring:url value="/resources/images/pick65 (1).png" htmlEscape="true" var="error3"/>
          
            <img id="f3" class="img-responsive" style="border: 2px solid;border-radius:15px; color: #E8CEF5;" src="${error3}"/>
		<br>
		<button class="btn btn-default" style = "margin-left:30%" onclick="window.location.href='https://youtu.be/YvKUSkq8dEg'"><span>¡Aprende a jugar!</span></button>
		
		<h2></h2>
		<br></br><br>
		    
		    
		<p>
        </div>
          
          <div class="col-md-4">
     <br></br>
      
		<p>
		<spring:url value="/resources/images/img23 (1).jpg" htmlEscape="true" var="error2"/>
            <img id="f4" class="img-responsive" style=" border: 2px solid; border-radius:15px;color: #E8CEF5;" src="${error2}"/>
            
        <br>    
		<button class="btn btn-default" style = "margin-left:27%" onclick="window.location.href='personas'"><span>Historial de partidas</span></button>
		
		
		<h2></h2>
		<br></br><br>
		
		
		<p>
        </div>
      </div>
      </section>  
      </security:authorize>
      
      <security:authorize access="hasAuthority('admin')">
      
      <section id="sectionB">
      <br></br><br>
      <br></br><br>
      <div align = "center" style="margin-left:20px;margin-top:-20px;">
      
      <spring:url value="/resources/images/petris_detalle_progreso1.jpg" htmlEscape="true" var="error6"/>
            <img id="f5"  width="720" height = "450" class="img-responsive" style=" border: 2px solid; border-radius:15px;color: #E8CEF5;" src="${error6}"/>
            
        <br>    
		<button class="btn btn-default" style = "margin-left:-1%" onclick="window.location.href='/games/herramientasAdmin'" ><span>Administración</span></button>
		
      
      </div>
      </section>
      </security:authorize>
      			<br></br><br>
      
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
</html>
