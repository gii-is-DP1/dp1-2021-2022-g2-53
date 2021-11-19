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
            <petclinic:inputField label="Black players points" name="pointsBlack"/>
            <petclinic:inputField label="Red players points" name="pointsRed"/>
            <petclinic:inputField label="Board" name="board"/>

        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
            
                <button class="btn btn-default" type="submit">create game</button>
            </div>
        </div>
    </form:form>
</petclinic:layout>