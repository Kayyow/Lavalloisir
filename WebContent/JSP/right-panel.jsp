<%@ page pageEncoding="UTF-8" %>
<div id="rightPanel">

 	<div class="columnBox connection">
    	<div class="titleBox">Se connecter</div>
	    <form id="cnctForm">
	        <label for="cnctLogin">Identifiant :</label>
	        <input type="text" class="cnctInput login" id="cnctLogin" name="login" placeholder="Identifiant">
	
	        <label for="cnctPwd">Mot de passe : </label>
	        <input type="text" class="cnctInput pwd" id="cnctPwd" name="password" placeholder="••••••••••••">
	        <a href="Registration">S'inscrire</a>
	        <input type="submit" value="Connexion" id="cnctButton">
	    </form>

	</div>
	<div class="columnBox bestRating">
		<div class="titleBox">Les mieux notés</div>
		<ul>
			<li class="listBestRating">TOP 1</li>
			<li class="listBestRating">TOP 2</li>
			<li class="listBestRating">TOP 3</li>
			<li class="listBestRating">TOP 4</li>
			<li class="listBestRating">TOP 5</li>
		</ul>
	</div>
	<div class="columnBox lastAdded">
		<div class="titleBox">Les derniers ajoutés</div>

	</div>
</div>