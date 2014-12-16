<%@ page pageEncoding="UTF-8" %>

<h2> Liste des loisirs </h2>
<hr>

<form id="leisr">	
	
	<label for="choiceLeisr">Catégorie :</label>
	<select id="choiceLeisr">
	    <option >Categorie 1</option>
	    <option >Categorie 2</option>
	    <option >Categorie 3</option>
	    <option >Categorie 4</option>
	</select>
	
	<a href="CreateLeisure" id="addLeisurButton"> Ajouter un loisir</a>
</form>

<!-- Affiche la liste des activités -->
<div id="leisrList">

	<!-- Tant qu'il y a des activités, afficher : -->
	<div class="leisrInsert">
	    <div class="leisrPhoto"></div>
        <div class="leisrDesctription">
        	<span class="leisrTitle">Activité 1</span><br>
        	<div class="leisrRating"> <span class="leisrValueRating">6.5</span> /10 </div>
        	Adresse : <br>
    	</div>
	</div>
	
	<%
		
	%>
    
</div> 