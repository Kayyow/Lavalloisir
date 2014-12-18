<%@ page pageEncoding="UTF-8" %>

<h2> Donne-nous ton avis </h2>
<hr>

<form method="post">
	
	<label for="leisrRating">Loisirs :</label>	
	<select class="formInput" id="leisrId" name="leisrId" class="formInput">
		<c:choose>
			<c:when test="${ leisures != null }">
				<c:forEach var="leisure" items="${ leisures }">
					<option value="${ leisure.id }">${ leisure.name }</option>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<option>PAS DE LOISIR</option>
			</c:otherwise>
		</c:choose>
	</select>
	
	<label for="leisrRate">Note (<b>/10</b>) :</label>
	<input type="text" class="formInput" id="leisrScore" name="leisrScore" placeholder="0">
	
	<input class="button" type="submit" value="Evaluer" />
</form>