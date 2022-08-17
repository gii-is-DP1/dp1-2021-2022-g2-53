<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<body style= "background: url(/resources/images/backgroundPetris.jpg)">

<petclinic:layout pageName="games">

    <form:form modelAttribute="game" class="form-horizontal"  action="/games/saveToken">
         <div class="form-group has-feedback">
            
            <h2 align="center">Inserta el código</h2>

        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10" style = "margin-left:2%">
            	<petclinic:inputField  label="token" name="token"/>
                
            </div>
        </div>
    </form:form>
    
    <div class="row" >
	<div class="col-md-4">
            
        </div>
	<div class="col-md-4" >
      
		<p>
		<spring:url value="/resources/images/pick65 (1).png" htmlEscape="true" var="error3"/>
          
            <img id="f3" class="img-responsive" style="border: 2px solid;border-radius:15px; color: #E8CEF5;" src="${error3}"/>
        <br> 
            
        <br>
        
            <button class="btn btn-default"  style = "margin-left:30.5%" type="submit"><span>Unirte a partida</span></button>
		
        </div>
     <div class="col-md-2">
       
        </div>
  </div>     
    
    
</petclinic:layout>