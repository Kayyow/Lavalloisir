<%@ page pageEncoding="UTF-8" %>
<h2>Se connecter</h2>
<hr>

<form id="cnctForm" method="post">	
	<div class="formField">	
		<label for="Login">Identifiant :</label>
		<input type="text" class="formInput login" name="Login" value="<c:out value="${user.login}"/>" placeholder="Login" />
		<span class="error">${form.errors['cnctLogin']}</span>
	</div>
	
	<div class="formField">	
		<label for="password">Mot de passe : </label>
		<input type="password" class="formInput pwd" name="password" value="" placeholder="•••••••••••••••"/>
		<span class="error">${form.errors['cnctPassword']}</span>
	</div>
	
	<a href="Registration" class="registrButton">S'inscrire</a>
   	
	<input type="submit" value="Connexion">
        
	<p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
    
	<%-- Vérification de la présence d'un objet user en session --%>
	<c:if test="${!empty sessionScope.sessionUser}">
		<%-- Si l'utilisateur existe en session, alors on affiche son login --%>
		<p class="success">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUser.login}</p>
	</c:if>
</form>