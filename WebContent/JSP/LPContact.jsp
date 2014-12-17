<h2> Contact </h2>
<hr>

<form id="contactForm" method="post">

	<div class="formField">		
		<label for="contactName">Name :</label>
		<input type="text" class="formInput" id="contactName" name="contactName" placeholder="Nom Prénom">
	</div>
	
	<div class="formField">		
		<label for="contactEmail">Email :</label>
		<input type="text" class="formInput" id="contactEmail" name="contactEmail" placeholder="adresse@domaine.fr">
	</div>
	
	<div class="formField">		
		<label for="contactSubject">Objet :</label>
		<input type="text" class="formInput" id="contactSubject" name="contactSubject" placeholder="Objet">
	</div>
	
	<div class="formField">
		<label for="contactMessage">Objet :</label>
		<textarea id="contactMessage" class="formInput" name="contactMessage" placeholder="Ecrivez votre message"></textarea>
	</div>
	
	<input type="submit" value="S'inscrire">
</form>