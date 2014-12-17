<%@ page pageEncoding="UTF-8" %>

<h2> Liste des loisirs </h2>
<hr>
<a href="/Lavalloisir/restrained/CreateLeisure" id="addLeisurButton">Ajouter un loisir</a>
<form method="post">
	
	<label for="categoryLeisr">Catégorie :</label>	
	<select id="categoryLeisr" name="categoryLeisr" class="formInput">
		<c:choose>
			<c:when test="${ categories != null }">
				<c:forEach var="category" items="${ categories }">
					<option value="${ category.id }">${ category.title }</option>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<option>PAS DE CATEGORIE</option>
			</c:otherwise>
		</c:choose>
	</select>
	
	<input class="button" type="submit" value="Trier" />
</form>

<!-- Affiche la liste des loisirs -->
<div id="leisrList">

	<!-- Tant qu'il y a des loisirs, afficher : -->	
	<c:forEach var="leisure" items="${ leisures }">
		<div class="leisrInsert">
			<div class="leisrPhoto"></div>
			<div class="leisrRating">
				<span class="leisrValueRating">6.5</span> /10
			</div>
			<div class="leisrDescription">
				<span class="leisrTitle">${ leisure.name }</span><br/>

				<p class="leisrOtherInfo">
					<p>Descriptifs :<span> ${ leisure.description }</span></p>
						Adresse : <span> ${ leisure.address }</span><br/>
						Téléphone : <span> ${ leisure.phone }</span><br/>
						Email : <span> ${ leisure.email }</span>
				</p>
			</div>
		</div>
	</c:forEach>
    
</div> 