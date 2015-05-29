<%@ page pageEncoding="UTF-8" %>
<html>
<%@ include file="_head.jsp" %>
<body>
	<div id="body-bg">
	</div>
	<div id="container">
		<div class="splitter">
			<%@ include file="header.jsp"%>
	
			<div class="coreContent">
				<%@ include file="right-panel.jsp"%>
				
				<div id="leftPanel">
					<jsp:include page="${ fileLP }" flush="true"/>
				</div>
			</div>
			
			<%@ include file="footer.jsp"%>
			<div class="site-cache"></div>
		</div>
	</div>
	
    <script type="text/javascript" src="/Lavalloisir/JS/jquery-2.1.3.js"></script>
    <script type="text/javascript" src="/Lavalloisir/JS/jquery.dotdotdot.min.js"></script>
    <script type="text/javascript" src="/Lavalloisir/JS/dotdotdot_script.js"></script>
    <script type="text/javascript" src="/Lavalloisir/JS/script.js"></script>
</body>
</html>