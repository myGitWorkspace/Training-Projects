<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>

<jsp:include page="block/header.jsp" />

<div class="container">

	<h1><spring:message code="category.list.title"/></h1>
	<br />

	<spring:url value="/add" var="categoryAddActionUrl" />
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<div>
	<form name='categoryForm' action="<c:url value='/category' />" method='GET'>
		<button type="submit" class="btn-lg btn-primary pull-left"><spring:message code="button.createcategory"/></button>
		<input type="hidden" name="action" value="add" />		
	</form>
	</div>
	</sec:authorize>
	<br><br>
	
	<table class="table table-bordered table-hover table-striped">
	<tr>
		<th><spring:message code="category.list.catname.label"/></th>
		<th><spring:message code="category.list.numtests.label"/></th>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		<th></th>
		</sec:authorize>
	</tr>
	
	<c:forEach var="category" items="${categories}">
		<tr>
			<td><a href="<c:url value='/category/${category.categoryId}' />"><c:out value="${category.name}"/></a></td>			
			<td><c:out value="${category.test.size()}"/></td>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<td>
				<spring:url value="/category" var="categoryDeleteActionUrl" />
				<form:form name="categoryDeleteForm" action="${categoryDeleteActionUrl}" method='GET'>
				<input type="hidden" name="action" value="delete"/>
				<input type="hidden" name="categoryId" value="${category.categoryId}"/>
				<button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="button.delete"/></button>
				</form:form>
			</td>
			</sec:authorize>
		</tr>
	</c:forEach>	
	
	</table>

</div>

<jsp:include page="block/footer.jsp" />

</body>
</html>