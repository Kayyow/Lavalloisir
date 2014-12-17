<%@page import="com.lavalloisir.beans.business.Category"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8" %>
<h2> Ajouter un loisir </h2>
<hr>

<form id="createLeisrForm" method="post">
	
	<div class="formField">
		<label for="nameLeisr">Nom :</label>
		<input type="text" class="formInput" id="nameLeisr" name="nameLeisr"
			placeholder="Nom du loisir" value="<c:out value="${leisure.name}"/>"/>
		<span class="error">${ form.errors['nameLeisr'] }</span>
	</div>
	
	<div class="formField">
		<label for="addressLeisr">Adresse :</label>
		<textarea id="addressLeisr" name="addressLeisr" class="formInput" placeholder="#0 allée Adresse #0000 VILLE"></textarea>
		<span class="error">${ form.errors['addressLeisr'] }</span>
	</div>
	
	<div class="formField">
		<label for="phoneLeisr">Telephone :</label>
		<input type="text" class="formInput" id="phoneLeisr" name="phoneLeisr"
			placeholder="Telephone" value="<c:out value="${leisure.phone}"/>"/>
		<span class="error">${ form.errors['phoneLeisr'] }</span>
	</div>
	
	<div class="formField">
		<label for="emailLeisr">Email :</label>
		<input type="text" class="formInput" id="emailLeisr" name="emailLeisr"
			placeholder="adresse@domaine.fr" value="<c:out value="${leisure.email}"/>"/>
		<span class="error">${ form.errors['emailLeisr'] }</span>
	</div>
	
	<div class="formField">
		<label for="categoryLeisr">Catégorie :</label>
		<select id="categoryLeisr" name="categoryLeisr" class="formInput">
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
		<span class="error">${ form.errors['categoryLeisr'] }</span>
	</div>
	
	<div class="formField">
		<label for="descriptionLeisr">Description :</label>
		<textarea id="descriptionLeisr" name="descriptionLeisr" class="formInput"
			placeholder="Description du loisir"></textarea>
		<span class="error">${ form.errors['descriptionLeisr'] }</span>
	</div>
		
	<input class="button" type="submit" value="Ajouter">
</form>