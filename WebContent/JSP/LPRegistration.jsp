<%@ page pageEncoding="UTF-8" %>
<h2>Inscription</h2>
<hr>

<form id="registrForm" method="post">
	
	<div class="formField">					
		<label for="registrLName">Nom :</label>
		<input type="text" class="formInput" name="registrLName" placeholder="Nom" value="<c:out value="${user.name}"/>"/>
		<span class="error">${form.errors['registrLName']}</span>
	</div>
	
	<div class="formField">	
		<label for="registrFName">Prénom :</label>
		<input type="text" class="formInput" name="registrFName" placeholder="Prénom" value="<c:out value="${user.firstName}"/>"/>
		<span class="error">${form.errors['registrFName']}</span>
	</div>
	
	<div class="formField">
		<label for="registrMail">Adresse e-mail :</label>
		<input type="text" class="formInput" name="registrMail" placeholder="adresse@domaine.fr" value="<c:out value="${user.email}"/>"/>
		<span class="error">${form.errors['registrMail']}</span>
	</div>
	
	<div class="formField">
		<label for="registrLogin">Login :</label>
		<input type="text" class="formInput" name="registrLogin" placeholder="Login" value="<c:out value="${user.login}"/>"/>
		<span class="error">${form.errors['registrLogin']}</span>
	</div>
	
	<div class="formField">
		<label for="registrPwd">Mot de passe :</label>
		<input type="password" class="formInput" name="registrPwd" placeholder="••••••••••••••" value=""/>
		<span class="error">${form.errors['registrPwd']}</span>
	</div>
	
	<div class="formField">
		<label for="registrConfirm">Confirmation :</label>
		<input type="password" class="formInput" name="registrConfirm" placeholder="••••••••••••••" value=""/>
		<span class="error">${form.errors['registrConfirm']}</span>
	</div>
	
	<input type="submit" value="S'inscrire">
</form>