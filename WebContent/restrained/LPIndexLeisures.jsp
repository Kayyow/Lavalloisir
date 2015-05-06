<%@ page pageEncoding="UTF-8" %>
<h2>Liste des loisirs</h2>
<hr>

<div class="list-leisure">
	<c:forEach items="${ leisures }" var="leisure">
		<div class="leisure-content">
			<span class="title">${ leisure.title }</span>
			<span class="category">${ leisure.category.label }</span>
			<span>${ leisure.email } - ${ leisure.phone }</span>
			<span>${ leisure.address.number } ${ leisure.address.street } - ${ leisure.address.zipCode } ${ leisure.address.city }</span>
			<span>${ leisure.description }</span>
		</div>
	</c:forEach>
</div>