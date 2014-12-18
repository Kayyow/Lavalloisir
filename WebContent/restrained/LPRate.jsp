<%@ page pageEncoding="UTF-8" %>

<h2> Donne-nous ton avis </h2>
<hr>

<form method="post">
	
	<label for="leisrRating">Catégorie :</label>	
	<select class="formInput" id="leisrRating" name="leisrRating" class="formInput">
		<c:choose>
			<c:when test="${ leisures != null }">
				<c:forEach var="leisure" items="${ leisures }">
					<option value="${ leisure.id }">${ leisure.title }</option>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<option>PAS DE LOISIR</option>
			</c:otherwise>
		</c:choose>
	</select>
	
	<label for="leisrRate">Catégorie :</label>
	<input type="text" class="formInput" id="leisrRate" name="leisrRate" placeholteur="0">
	
	<input class="button" type="submit" value="Evaluer" />
</form>