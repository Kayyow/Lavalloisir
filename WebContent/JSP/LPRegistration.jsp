<%@ page pageEncoding="UTF-8" %>

<h2> Inscription </h2>
<hr>
<form id="registrForm" method="post">					
	<label for="registrLName">Nom :</label>
	<input type="text" class="registrInput" name="registrLName" id="registrLName" placeholder="Nom"/>
	
	<label for="registrFName">Prénom :</label>
	<input type="text" class="registrInput" name="registrFName" id="registrFName" placeholder="Prénom"/>
	
	<label for="registrMail">Adresse e-mail :</label>
	<input type="text" class="registrInput" name="registrMail" id="registrMail" placeholder="adresse@domaine.fr"/>
	
	<label for="registrLogin">Login :</label>
	<input type="text" class="registrInput" name="registrLogin" id="registrLogin" placeholder="Login"/>
	
	<label for="registrPwd">Mot de passe :</label>
	<input type="password" class="registrInput" name="registrPwd" id="registrPwd" placeholder="••••••••••••"/>
	
	<label for="registrConfirm">Confirmation :</label>
	<input type="password" class="registrInput" name="registrConfirm" id="registrConfirm" placeholder="••••••••••••"/>
	
	<input id="registrButton" type="submit">
</form>