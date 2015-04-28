<%@ page pageEncoding="UTF-8" %>
<h2>Inscris-toi</h2>
<hr>

<form id="registrForm" method="post">
	
	<div class="formField">					
		<label for="name">Nom :</label>
		<input type="text" class="formInput" name="name" placeholder="Nom" value="<c:out value="${user.name}"/>"/>
		<span class="error">${form.errors['name']}</span>
	</div>
	
	<div class="formField">	
		<label for="givenName">Prénom :</label>
		<input type="text" class="formInput" name="givenName" placeholder="Prénom" value="<c:out value="${user.givenName}"/>"/>
		<span class="error">${form.errors['givenName']}</span>
	</div>
	
	<div class="formField">
		<label for="email">Adresse e-mail :</label>
		<input type="text" class="formInput" name="email" placeholder="adresse@domaine.fr" value="<c:out value="${user.email}"/>"/>
		<span class="error">${form.errors['email']}</span>
	</div>
	
	<div class="formField">
		<label for="password">Mot de passe :</label>
		<input type="password" class="formInput" name="password" placeholder="••••••••••••••" value=""/>
		<span class="error">${form.errors['password']}</span>
	</div>
	
	<div class="formField">
		<label for="confirmPassword">Confirmation :</label>
		<input type="password" class="formInput" name="confirmPassword" placeholder="••••••••••••••" value=""/>
		<span class="error">${form.errors['confirmPassword']}</span>
	</div>
	
	<div class="formField">					
		<label for="phone">Téléphone :</label>
		<input type="text" class="formInput" name="phone" placeholder="0612345678" value="<c:out value="${user.phone}"/>"/>
		<span class="error">${form.errors['phone']}</span>
	</div>
	
	<div class="formField">					
		<label for="profilPicture">Photo de profil :</label>
		<input type="file" class="formInput" name="profilPicture" value="<c:out value="${user.picture}"/>"/>
		<span class="error">${form.errors['profilPicture']}</span>
	</div>
	
	<input type="submit" value="S'inscrire">
</form>