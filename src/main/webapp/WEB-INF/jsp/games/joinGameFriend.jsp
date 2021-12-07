<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="games">

    <form:form modelAttribute="game" class="form-horizontal"  action="/games/saveToken">
         <div class="form-group has-feedback">
            
            <h2>Inserta el codigo</h2>

        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            	<petclinic:inputField label="token" name="token"/>
                <button class="btn btn-default" type="submit">Unirte a partida</button>
            </div>
        </div>
    </form:form>
    
    
</petclinic:layout>