<html>
<%@ include file="head.jsp"%>
<body>
	<div id="container">
		<%@ include file="header.jsp"%>

		<div id="coreContent">
			<div id="leftPanel">
				<jsp:include page="${ fileName }" flush="true" />
			</div>

			<%@ include file="right-panel.jsp"%>
		</div>

		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>