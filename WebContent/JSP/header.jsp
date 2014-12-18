<header>
	<!-- Titre / Logo -->
	<h1>
		<a href="/Lavalloisir/Home">Laval<span id="logoTitleColor">loisir</span></a>
	</h1>

	<!-- Menu -->
	<nav id="menu">
		<ul>
			<li class="tabLink"><a href="/Lavalloisir/Home">Accueil</a></li>
			<li class="tabLink"><a href="/Lavalloisir/restrained/DisplayLeisure">Loisir</a></li>
			<li class="tabLink"><a href="/Lavalloisir/restrained/Rate">Evaluer</a></li>
			<li class="tabLink"><c:choose>
				<c:when test="${ sessionScope.sessionUser != null }">
					<span id="registrationUnlinked">Inscription</span>
				</c:when>
				
				<c:otherwise>
					<a href="/Lavalloisir/Registration">Inscription</a>
				</c:otherwise>
			</c:choose></li>
			<li class="tabLink"><a href="/Lavalloisir/Contact">Contact</a></li>
		</ul>
	</nav>

	<!-- Bouton menu responsive -->
	<a href="#" class="buttonMenu"></a>
</header>