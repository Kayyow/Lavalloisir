<%@ page pageEncoding="UTF-8" %>

<h2> Ajouter un loisir </h2>
<hr>

<form id="createLeisrForm" method="post">					
	
	<div class="formField">
		<label class="nameA" for="nameLeisr">Nom :</label>
		<input type="text" class="nameA createLeisrInput" id="nameLeisr" name="nameLeisr" placeholder="Nom du loisir"/>
	</div>
	
	<div class="formField">
		<label for="numberLeisr">N° :</label>
		<input type="text" class="createLeisrInput" id="numberLeisr" name="numberLeisr" placeholder="Numéro de la rue"/>
	</div>
	
	<div class="formField">
		<label for="streetLeisr">Rue :</label>
		<input type="text" class="createLeisrInput" id="streetLeisr" name="streetLeisr" placeholder="Nom rue"/>
	</div>
	
	<div class="formField">
		<label for="zipCodeLeisr">Code Postal :</label>
		<input type="text" class="createLeisrInput" id="zipCodeLeisr" name=zipCodeLeisr placeholder="Code Postal"/>
	</div>
	
	<div class="formField">
		<label for="cityLeisr">Ville :</label>
		<input type="text" class="createLeisrInput" id="cityLeisr" name="cityLeisr" placeholder="Ville"/>
	</div>
	
	<div class="formField">
		<label for="phoneLeisr">Telephone :</label>
		<input type="text" class="createLeisrInput" id="phoneLeisr" name="phoneLeisr" placeholder="Telephone"/>
	</div>
	
	<div class="formField">
		<label for="emailLeisr">Email :</label>
		<input type="text" class="createLeisrInput" id="emailLeisr" name="emailLeisr" placeholder="adresse@domaine.fr"/>
	</div>
	
	<div class="formField">
		<label for="choiceLeisr">Categorie :</label>
		<select id="choiceLeisr" class="createLeisrInput">
		    <option >Categorie 1</option>
		    <option >Categorie 2</option>
		    <option >Categorie 3</option>
		    <option >Categorie 4</option>
		</select>
	</div>
	
	<div class="formField">
		<label for="descriptionLeisr">Description :</label>
		<textarea id="descriptionLeisr" rows="10" name="descriptionLeisr" class="createLeisrInput"></textarea>
	</div>
		
	<input class="button" type="submit">
</form>