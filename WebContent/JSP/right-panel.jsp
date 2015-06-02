<%@ page pageEncoding="UTF-8" %>

<div id="rightPanel">
	<div class="columnBox" id="connectionBox">
	
	    <div class="titleBox">
	    	<a id="cnctLink" href="/Lavalloisir/<c:out value="${!empty sessionScope.user ? 'Deconnection' : 'Connection'}"/>">
	    		<img src="/Lavalloisir/img/power_button_<c:out value="${!empty sessionScope.user ? 'red' : 'green'}"/>.svg"/>
	    	</a>
	    	<a href="/Lavalloisir/<c:out value="${!empty sessionScope.user ? 'restrained/UpdateAccount' : 'Registration'}"/>">
	    		<c:out value="${!empty sessionScope.user ? 'Mon compte' : 'Inscription'}"/></a>
	    </div>
	     
		<c:if test="${ sessionScope.user != null }">
			<div class="connectionBox info">
	    	<p>
		    	<c:out value="${ sessionScope.user.givenName }"/>
		    	<c:out value="${ sessionScope.user.name }"/>
	    	</p>
	    	<c:if test="${ sessionScope.user.email != null }">
	    		<p> <c:out value="${ sessionScope.user.email }"/> </p>
	    	</c:if>
	    	
	    	<c:if test="${ sessionScope.user.displayPhone != null }">
	    		<p> <c:out value="${ sessionScope.user.displayPhone }"/> </p>
	    	</c:if>
	    	
	    	</div>
	    </c:if>		

	 </div>
	
	<div class="columnBox" id="bestRating">
	    <div class="titleBox">Les mieux notés</div>
	    
		<c:choose>
			<c:when test="${ sessionScope.user != null }">
				<ul>
			        <c:forEach items="${ bestLeisures }" var="leisureNote">
			        	<li class="listBestRating"> <c:out value="${ leisureNote }"/> </li>
			        </c:forEach>
	    		</ul>
			</c:when>
			<c:otherwise>
				<span>Fonctionnalité réservée au membre du site ! </span>
			</c:otherwise>
		</c:choose>
	    
	</div>
</div>