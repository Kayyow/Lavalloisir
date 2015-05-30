<%@ page pageEncoding="UTF-8" %>
<h2>Liste des loisirs</h2>
<hr>

<form method="post">
	<label for="category">Catégories :</label>	
	<select name="category" class="category">
		<c:choose>
			<c:when test="${ categories != null }">
				<c:forEach items="${ categories }" var="category" >
					<option value="${ category.id }"> <c:out value="${ category.label }"/> </option>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<option>PAS DE CATEGORIE</option>
			</c:otherwise>
		</c:choose>
	</select>
	
	<input class="button" type="submit" value="Trier" />
</form>

<div class="list-leisures">
	<c:forEach items="${ leisures }" var="leisure">
		<a class="content-leisure" href="/Lavalloisir/restrained/ShowLeisure?id=<c:out value="${ leisure.id }"/>">
			<h3> <c:out value="${ leisure.title }"/> </h3>
			
			<p>
				<span class="label">Catégorie :</span> <span class="category"> <c:out value="${ leisure.category.label }"/> </span>
			</p>
			
			<p>
				<span class="label">Adresse :</span> <c:out value="${ leisure.address.number } ${ leisure.address.street } ${ leisure.address.zipCode } ${ leisure.address.city }"/>
			</p>
			
			<p>
				<span class="label">Description :</span> <span class="description"> <c:out value="${ leisure.description }"/> </span>
			</p>
		</a>
	</c:forEach>
</div>