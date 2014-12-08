<%@ page pageEncoding="UTF-8" %>

<h2> Liste des loisirs </h2>
<hr>

<form id="LeisureForm">					
	
	<label for="choiceLeisure">Recherche par catégorie:</label>
	<select id="choiceLeisure">
	    <option >Categorie 1</option>
	    <option >Categorie 2</option>
	    <option >Categorie 3</option>
	    <option >Categorie 4</option>
	</select>
	
	<input id="addLeisurButton" type="submit" value="Ajouter un loisir">
</form>


<div id="leisrList">

	<!-- Tant qu'il y a des activité, afficher : -->
	<div class="leisrInsert">
	    <div class="leisrPhoto"></div>
        <div class="leisrDesctription">
        	<span class="leisrTitle">Activité 1</span><br>
        	<div class="leisrRating"> <span class="leisrValueRating">6.5</span> /10 </div>
        	Localisation : <br>
        	Catégorie:
    	</div>
	</div>
    
</div>