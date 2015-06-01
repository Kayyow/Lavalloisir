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
	
	<div class="field">
		<span class="label">Avis</span>
		
		<span class="value noteAverage"> Note globale :  <c:out value="${ averageNote }"/><b>/10</b> </span>
		
		<form id="evaluationForm" method="post">
			<div class="formField">	
				<label for="inputNote">Note :</label>
				<input type="range" name="inputNote" class="formInput note"  min="0" max="10" step="1"/>
				<label for="inputOpinion"> Commentaire : </label>
				<textarea  name="inputOpinion" class="formInput opinion" rows="5" ></textarea>
				<input type="submit"  class="evaluateButton" value="Envoyer"/>
			</div>
		</form>
			
		<c:forEach items="${ evaluations }" var="evaluation">
			<hr>
			<span class="value note"> <c:out value="${ evaluation.note }"/> <b> /10</b> </span> </span>
			<span class="value nameUser"> <c:out value="${ evaluation.user.name }"/> <c:out value="${ evaluation.user.givenName }"/> :</span>
			<span class="value opinion"> <c:out value="${ evaluation.opinion }"/> </span>
		</c:forEach>
	</div>
</div>