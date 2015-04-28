<%@ page pageEncoding="UTF-8" %>

<div id="rightPanel">
	<div class="columnBox" id="connectionBox">
	
	    <div class="titleBox">
	    	<a id="cnctLink" href="/Lavalloisir/<c:out value="${!empty sessionScope.sessionUser ? 'Deconnection' : 'Connection'}"/>">
	    		<img src="/Lavalloisir/img/power_button_<c:out value="${!empty sessionScope.sessionUser ? 'red' : 'green'}"/>.svg"/>
	    	</a>
	    	<a href="#">Mon compte</a>
	    </div>
	    
	    
		<c:if test="${ sessionScope.sessionUser != null }">
			<div class="connectionBox info">
	    	<p>
		    	<c:out value="${ sessionScope.sessionUser.givenName }"/>
		    	<c:out value="${ sessionScope.sessionUser.name }"/>
	    	</p>
	    	<c:if test="${ sessionScope.sessionUser.email != null }">
	    		<p> <c:out value="${ sessionScope.sessionUser.email }"/> </p>
	    	</c:if>
	    	
	    	<c:if test="${ sessionScope.sessionUser.phone != null }">
	    		<p> <c:out value="${ sessionScope.sessionUser.phone }"/> </p>
	    	</c:if>
	    	
	    	</div>
	    </c:if>		

	 </div>
	
	<div class="columnBox" id="bestRating">
	    <div class="titleBox">Les mieux notés</div>
	    <ul>
	        <li class="listBestRating"> TOP 1 </li>
	        <li class="listBestRating"> TOP 2 </li>
	        <li class="listBestRating"> TOP 3 </li>
	        <li class="listBestRating"> TOP 4 </li>
	        <li class="listBestRating"> TOP 5 </li>
	    </ul>
	</div>
</div>