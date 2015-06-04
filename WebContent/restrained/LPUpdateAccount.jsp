<%@ page pageEncoding="UTF-8" %>
<h2>Mon compte</h2>
<hr>

<form id="updateAccountForm" method="POST">
	<img src="<c:out value="${sessionScope.user.picture}"/>"/>
	 
	<div class="formField">
		<label for="name">Nom :</label>
		<input type="text" class="formInput" name="name"
			value="<c:out value="${sessionScope.user.name}"/>"/> 
		<span class="error">${ form.errors["name"] }</span>
	</div>
	
	<div class="formField">
		<label for="name">Prénom :</label>
		<input type="text" class="formInput" name="givenName"
			value="<c:out value="${sessionScope.user.givenName}"/>"/>
		<span class="error">${ form.errors["givenName"] }</span>
	</div>
	
	<div class="formField">
		<label for="email">Email :</label>
		<input type="text" class="formInput" name="email"
			value="<c:out value="${sessionScope.user.email}"/>"/>
		<span class="error">${ form.errors["email"] }</span>
	</div>
	
	<div class="formField">
		<label for="phoneNum">Téléphone :</label>
		<input type="text" class="formInput" name="phone"
			value="<c:out value="${sessionScope.user.phone}"/>"/>
		<span class="error">${ form.errors["phone"] }</span>
	</div>
	
	<div class="formField">
		<label for="registrDate">Date d'inscription :</label>
		<input type="text" class="formInput"
			value="${ sessionScope.user.registration }" disabled/>
	</div>
	
	<input class="button" type="submit" value="Enregistrer" />
	<input type="hidden" name="whichForm" value="update"/>
</form>

<form id="deleteAccountForm" method="POST">
	<input class="button" type="submit" value="Supprimer le compte"/>
	<input type="hidden" name="whichForm" value="delete"/>
</form>