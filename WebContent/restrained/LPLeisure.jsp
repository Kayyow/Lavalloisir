<%@ page pageEncoding="UTF-8" %>

<h2> Liste des loisirs </h2>
<hr>
<a href="/Lavalloisir/restrained/CreateLeisure" id="addLeisurButton">Ajouter un loisir</a>
<form method="post">
	
	<label for="category">Catégorie :</label>	
	<select id="categoryLeisr" name="category" class="formInput">
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
			<img class="leisrPhoto" src="/Lavalloisir/img/leisure_img.svg">
			<div class="leisrRating">
				<span class="leisrValueRating">${ leisure.average }</span> /10
			</div>
			<div class="leisrDescription">
				<span class="leisrTitle">${ leisure.name }</span><br/>

				<div class="leisrOtherInfo">
					<p><b>Descriptifs :</b> ${ leisure.description }</p>
						<b>Adresse :</b> ${ leisure.address }<br/>
						<b>Téléphone :</b> ${ leisure.phone }<br/>
						<b>Email :</b> ${ leisure.email }
				</div>
			</div>
		</div>
	</c:forEach>
    
</div> 