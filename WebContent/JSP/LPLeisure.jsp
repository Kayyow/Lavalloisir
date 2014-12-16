<%@ page pageEncoding="UTF-8" %>

<h2> Liste des loisirs </h2>
<hr>

<form id="leisr">	
	
	<label for="categoryLeisr">Catégorie :</label>	
	<select id="categoryLeisr" name="categoryLeisr" class="createLeisrInput">
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
	
	<a href="CreateLeisure" id="addLeisurButton">Ajouter un loisir</a>
</form>

<!-- Affiche la liste des loisirs -->
<div id="leisrList">

	<!-- Tant qu'il y a des loisirs, afficher : -->	
	<c:forEach var="leisure" items="${ leisures }">
		<div class="leisrInsert">
			<div class="leisrPhoto"></div>
			<div class="leisrDescription">
				<span class="leisrTitle">${ leisure.name } :</span><br/>
				<div class="leisrRating">
					<span class="leisrValueRating">6.5</span> /10
				</div>
				<p>${ leisure.description }</p>
				<p>
					Adresse : ${ leisure.address }<br/>
					Téléphone : ${ leisure.phone }<br/>
					Email : ${ leisure.email }
				</p>
			</div>
		</div>
	</c:forEach>
    
</div> 