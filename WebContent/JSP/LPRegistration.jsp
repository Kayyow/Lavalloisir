<%@ page pageEncoding="UTF-8" %>
<h2>Inscription</h2>
<hr>

<form id="registrForm" method="post">					
	<label for="registrLName">Nom :</label>
	<input type="text" class="registrInput" name="registrLName" value="<c:out value="${user.name}"/>"/>
	<span class="error">${form.errors['registrLName']}</span>
	
	<label for="registrFName">Prénom :</label>
	<input type="text" class="registrInput" name="registrFName" value="<c:out value="${user.firstName}"/>"/>
	<span class="error">${form.errors['registrFName']}</span>
	
	<label for="registrMail">Adresse e-mail :</label>
	<input type="text" class="registrInput" name="registrMail" value="<c:out value="${user.email}"/>"/>
	<span class="error">${form.errors['registrMail']}</span>
	
	<label for="registrLogin">Login :</label>
	<input type="text" class="registrInput" name="registrLogin" value="<c:out value="${user.login}"/>"/>
	<span class="error">${form.errors['registrLogin']}</span>
	
	<label for="registrPwd">Mot de passe :</label>
	<input type="password" class="registrInput" name="registrPwd" value=""/>
	<span class="error">${form.errors['registrPwd']}</span>
	
	<label for="registrConfirm">Confirmation :</label>
	<input type="password" class="registrInput" name="registrConfirm" value=""/>
	<span class="error">${form.errors['registrConfirm']}</span>
	
	<input type="submit" value="S'inscrire">
</form>