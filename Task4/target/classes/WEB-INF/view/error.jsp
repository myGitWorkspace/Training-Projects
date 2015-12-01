<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<jsp:include page="block/header.jsp" />

<body>

	<div class="container">

		<h1><spring:message code="error.title"/></h1>
		<br><br>
		
		<p><b><spring:message code="error.cause.label"/> : </b>${exception}</p>
		<p><b><spring:message code="error.target.label"/> : </b>${url}</p>

	</div>

	<jsp:include page="block/footer.jsp" />

</body>
</html>