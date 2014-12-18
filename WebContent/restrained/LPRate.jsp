<%@ page pageEncoding="UTF-8" %>

<h2> Donne-nous ton avis </h2>
<hr>

<form id="rateForm" method="post">
	
	<label for="leisrRating">Loisir :</label>	
	<select class="formInput" id="leisrRating" name="leisrRating" class="formInput">
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
	
	<label for="rateScore">Score :</label>
	<input type="number" min="0" max="10" step="1" class="formInput" id="rateScore" name="rateScore"/>
	
	<input class="button" type="submit" value="Evaluer" />
</form>