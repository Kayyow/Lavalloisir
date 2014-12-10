<%@ page pageEncoding="UTF-8" %>

<h2> Ajouter un loisir </h2>
<hr>

<form id="createLeisrForm">					
	
	<label for="nameLeisr">Nom :</label>
	<input type="text" class="createLeisrInput" id="nameLeisr" placeholder="Nom du loisir"/><br/>
	
	<label for="numberLeisr">N° :</label>
	<input type="text" class="createLeisrInput" id="numberLeisr" placeholder="Numéro de la rue"/><br/>
	
	<label for="streetLeisr">Rue :</label>
	<input type="text" class="createLeisrInput" id="streetLeisr" placeholder="Nom rue"/><br/>
	
	<label for="zipCodeLeisr">Code Postal :</label>
	<input type="text" class="createLeisrInput" id="zipCodeLeisr" placeholder="Code Postal"/><br/>
	
	<label for="cityLeisr">Ville :</label>
	<input type="text" class="createLeisrInput" id="cityLeisr" placeholder="Ville"/><br/>
	
	<label for="phoneLeisr">Telephone :</label>
	<input type="text" class="createLeisrInput" id="phoneLeisr" placeholder="Telephone"/><br/>
	
	<label for="emailLeisr">Email :</label>
	<input type="text" class="createLeisrInput" id="emailLeisr" placeholder="adresse@domaine.fr"/><br/>
	
	<label for="choiceLeisr">Categorie :</label>
	<select id="choiceLeisr">
	    <option >Categorie 1</option>
	    <option >Categorie 2</option>
	    <option >Categorie 3</option>
	    <option >Categorie 4</option>
	</select>
	
	
	<input type="submit">
</form>