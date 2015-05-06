<%@ page pageEncoding="UTF-8" %>
<h2>Liste des loisirs</h2>
<hr>

<div class="list-leisures">
	<c:forEach items="${ leisures }" var="leisure">
		<div class="content-leisure">
			<h3> <c:out value="${ leisure.title }"/> </h3>
			<p> <span class="label">Catégorie :</span> <span class="category"> <c:out value="${ leisure.category.label }"/> </span> </p>
			<!-- <p> <span>Adresse email :</span> <c:out value="${ leisure.email }"/> </p>
			<p> <span>Numéro de téléphone :</span> <c:out value="${ leisure.phone }"/> </p> Affichés sur la page :show -->
			<p> <span class="label">Adresse :</span> <c:out value="${ leisure.address.number } ${ leisure.address.street } ${ leisure.address.zipCode } ${ leisure.address.city }"/> </p>
			<p> <span class="label">Description :</span> <span class="description"> <c:out value="${ leisure.description }"/> </span> </p>
		</div>
	</c:forEach>
</div>