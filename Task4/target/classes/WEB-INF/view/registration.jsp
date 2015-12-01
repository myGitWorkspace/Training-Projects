<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<jsp:include page="block/header.jsp" />

<div class="container" >
		
		<h1><spring:message code="registration.title"/></h1>
		<br />	
		
		<spring:url value="/register" var="registerActionUrl" />
		
<form:form name="userForm" modelAttribute="userForm" cssClass="form-horizontal registrationForm" action="${registerActionUrl}" method="POST">

	<div class="form-group">
		<label for="username" class="col-sm-2 control-label"><spring:message code="registration.username"/></label>
		<div class="col-sm-10">
			<form:input path="username" cssClass="form-control" />
			<form:errors path="username" cssStyle="color: red;"/>
		</div>
	</div>

	<div class="form-group">
		<label for="password" class="col-sm-2 control-label"><spring:message code="registration.password"/></label>
		<div class="col-sm-10">
			<form:password path="password" cssClass="form-control" />
			<form:errors path="password" cssStyle="color: red;"/>
		</div>
	</div>
	<div class="form-group">
		<label for="password2" class="col-sm-2 control-label"><spring:message code="registration.password.again"/></label>
		<div class="col-sm-10">
			<form:password path="password2" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-2">
			<input type="submit" value="<spring:message code="button.save"/>" class="btn btn-lg btn-primary" />
		</div>
	</div>
	<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
</form:form>

</div>

<jsp:include page="block/footer.jsp" />

</body>
</html>