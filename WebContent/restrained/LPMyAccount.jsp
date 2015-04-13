<h2>Mon compte</h2>
<hr>

<form id="myAccountForm">
	<img src="/Lavalloisir/img/account_img.svg" style="height:160px; width:160px; border-radius:10px"/>

	<div class="formField">
		<label for="name">Nom :</label>
		<input type="text" class="formInput" name="name"
			value="Doe"/>
		<span class="error"></span>
	</div>
	
	<div class="formField">
		<label for="name">Prénom :</label>
		<input type="text" class="formInput" name="givenName"
			value="John"/>
		<span class="error"></span>
	</div>
	
	<div class="formField">
		<label for="email">Email :</label>
		<input type="text" class="formInput" name="email"
			value="address@email.com"/>
		<span class="error"></span>
	</div>
	
	<div class="formField">
		<label for="password">Mot de passe</label>
		<input type="password" class="formInput" name="password"
			value=""/>
		<span class="error"></span>
	</div>
	
	<div class="formField">
		<label for="phoneNum">Téléphone :</label>
		<input type="text" class="formInput" name="phoneNum"
			value="0612345678"/>
		<span class="error"></span>
	</div>
	
	<div class="formField">
		<label for="registrDate">Date d'inscription :</label>
		<input type="text" class="formInput" name="registrDate"
			value="01/12/2014" disabled/>
		<span class="error"></span>
	</div>
	
	<input class="button" type="submit" value="Enregistrer" />
</form>