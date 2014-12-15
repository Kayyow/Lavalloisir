<%@ page pageEncoding="UTF-8" %>
<h2>Se connecter</h2>
<hr>

<form id="cnctForm" method="post">	
	<label for="cnctLogin">Identifiant :</label>
	<input type="text" class="cnctInput login" name="cnctLogin" value="<c:out value="${user.login}"/>" />
	<span class="error">${form.errors['cnctLogin']}</span>

	<label for="cnctPwd">Mot de passe : </label>
	<input type="password" class="cnctInput pwd" name="cnctPassword" value=""/>
	<span class="error">${form.errors['cnctPassword']}</span>
        
	<a href="Registration">S'inscrire</a>
        
	<input type="submit" value="Connexion">
        
	<p class="${empty form.errors ? 'success' : 'error'}">${form.result}</p>
    
	<%-- Vérification de la présence d'un objet user en session --%>
	<c:if test="${!empty sessionScope.sessionUser}">
		<%-- Si l'utilisateur existe en session, alors on affiche son login --%>
		<p class="success">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUser.login}</p>
	</c:if>
</form>