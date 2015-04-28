<%@ page pageEncoding="UTF-8" %>
<h2>Mon compte</h2>
<hr>

<form id="myAccountForm">
	<img src="<c:out value="${user.picture}"/>" style="height:160px; width:160px; border-radius:10px"/>
	
 <!-- /lavalloisir/img/account_img.svg -->
 
	<div class="formField">
		<label for="name">Nom :</label>
		<input type="text" class="formInput" name="name"
			value="<c:out value="${user.name}"/>"/> 
		<span class="error"></span>
	</div>
	
	<div class="formField">
		<label for="name">Prénom :</label>
		<input type="text" class="formInput" name="givenName"
			value="<c:out value="${user.givenName}"/>"/>
		<span class="error"></span>
	</div>
	
	<div class="formField">
		<label for="email">Email :</label>
		<input type="text" class="formInput" name="email"
			value="<c:out value="${user.email}"/>"/>
		<span class="error"></span>
	</div>
	
	<div class="formField">
		<label for="phoneNum">Téléphone :</label>
		<input type="text" class="formInput" name="phoneNum"
			value="<c:out value="${user.phone}"/>"/>
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