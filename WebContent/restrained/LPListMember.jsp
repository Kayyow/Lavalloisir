<%@ page pageEncoding="UTF-8" %>
<h2>Liste des Membres</h2>
<hr>

<div>

	<c:forEach items="${ users }" var="user">
	<p>
		<c:out value="${user.name }"/> <c:out value="${user.givenName }"/>
	</p>
		
	</c:forEach>
</div>
