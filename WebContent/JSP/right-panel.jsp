<%@ page pageEncoding="UTF-8" %>
<div id="rightPanel">
	<div class="columnBox" id="connectionBox">
	    <div class="titleBox">Mon compte
	    	<div id="cnctLink"><a href="Connection"></a></div>
	    </div>
	    <div>
	    	<p> Login : <c:out value="${ user.login }"/></p>
	    	<p> Nom : <c:out value="${ user.name }"/></p>
	    	<p> Prénom : <c:out value="${ user.firstName }"/></p>
	    </div>
	   
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
	<div class="columnBox" id="lastAdded">
	    <div class="titleBox">Les derniers ajoutés</div>
	        
	</div>
</div>