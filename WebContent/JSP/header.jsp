<header>
	<!-- Titre / Logo -->
	<h1>
		<a href="/Lavalloisir/Home">Laval<span id="logoTitleColor">loisir</span></a>
	</h1>

	<!-- Menu -->
	<nav>
		<a href="/Lavalloisir/Home">Accueil</a>
		
		<a href="/Lavalloisir/restrained/IndexLeisures">Loisir</a>
		
		<c:if test="${ sessionScope.user == null }">
			<a href="/Lavalloisir/Registration">Inscription</a>
		</c:if>
		
		<a href="/Lavalloisir/Contact">Contact</a>
	</nav>

	<!-- Bouton menu responsive -->
	<a href="#" class="buttonMenu"></a>
</header>