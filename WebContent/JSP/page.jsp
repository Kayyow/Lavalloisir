<%@ page pageEncoding="UTF-8" %>
<html>
<%@ include file="head.jsp" %>>
<body>
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
</body>
</html>