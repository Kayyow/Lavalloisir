<%@ page pageEncoding="UTF-8" %>
<h2> <c:out value="${ leisure.title }"/> </h2>
<hr>

<div class="show-leisure">
	<div class="picture">
		
	</div>
	
	<div class="field">
		<span class="label">Catégorie</span>
		
		<span class="value category"> <c:out value="${ leisure.category.label }"/> </span>
	</div>
	
	<div class="field">
		<span class="label">Email</span>
		
		<span class="value"> <c:out value="${ leisure.email }"/> </span>
	</div>
	
	<div class="field">
		<span class="label">N° de téléphone</span>
		
		<span class="value"> <c:out value="${ leisure.displayPhone }"/> </span>
	</div>
	
	<div class="field">
		<span class="label">Site web</span>
		
		<a href="${ leisure.website }" class="value"> <c:out value="${ leisure.website }"/> </a>
	</div>
	
	<div class="field">
		<span class="label">Adresse</span>
		
		<span class="value">
			<c:out value="${ leisure.address.number } ${ leisure.address.street }
							${ leisure.address.zipCode } ${ leisure.address.city }"/>
		</span>
	</div>
	
	<div class="field">
		<span class="label">Description</span>
		
		<span class="value description"> <c:out value="${ leisure.description }"/> </span>
	</div>
</div>