<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<jsp:include page="block/header.jsp" />

<div class="container">

	<h1><spring:message code="category.add.title"/></h1>
	<br />

	<spring:url value="/category" var="questionActionUrl" />
			
<form:form name="categoryForm" modelAttribute="categoryForm" cssClass="form-horizontal registrationForm" action="${questionActionUrl}" method="POST" accept-charset="UTF-8">			

	<div class="form-group">
		<label for="name" class="col-sm-2 control-label"><spring:message code="category.add.label"/></label>
		<div class="col-sm-10">
			<form:input path="name" cssClass="form-control" />
			<form:errors path="name" cssStyle="color: red;"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-2">
			<input type="submit" value="<spring:message code="button.save"/>" class="btn btn-lg btn-primary" />
		</div>
	</div>	

	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

</form:form>

</div>

<jsp:include page="block/footer.jsp" />

</body>
</html>