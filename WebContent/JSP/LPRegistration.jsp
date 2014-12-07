<%@ page pageEncoding="UTF-8" %>

<h2> Inscription </h2>
<hr>
<form id="registrForm">					
	<label for="registrLName">Nom :</label>
	<input type="text" class="registrInput" id="registrLName" placeholder="Nom"/>
	
	<label for="registrFName">Prénom :</label>
	<input type="text" class="registrInput" id="registrFName" placeholder="Prénom"/>
	
	<label for="registrMail">Adresse e-mail :</label>
	<input type="text" class="registrInput" id="registrMail" placeholder="adresse@domaine.fr"/>
	
	<label for="registrLogin">Login :</label>
	<input type="text" class="registrInput" id="registrLogin" placeholder="Login"/>
	
	<label for="registrPwd">Mot de passe :</label>
	<input type="password" class="registrInput" id="registrPwd" placeholder="••••••••••••"/>
	
	<label for="registrConfirm">Confirmation :</label>
	<input type="password" class="registrInput" id="registrConfirm" placeholder="••••••••••••"/>
	
	<input id="registrButton" type="submit">
</form>