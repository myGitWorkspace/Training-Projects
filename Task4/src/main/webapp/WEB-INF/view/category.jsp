<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>

<jsp:include page="block/header.jsp" />

<script>
function create_new_test() {
	if(document.getElementById("title").value == "") {
		var elem = document.getElementById("warning");
		elem.innerHTML = "<strong>Warning!</strong> Please, enter the title of the test";
		elem.className = elem.className + " alert alert-danger";
		return;		
	}
	document.testForm.submit();
}
</script>

<div class="container">

	<h1><spring:message code="category.title"/></h1>
	<br/>
	
	<div>
		<a href="<c:url value='/category' />">
			<spring:message code="breadcrumbs.allcategories"/>
		</a> > <c:out value="${category.name}"/>
	</div>
	
	<spring:url value="/add" var="categoryAddActionUrl" />
	
	<br><br>
	
	<div>

	<sec:authorize access="hasRole('ROLE_ADMIN')">
	<form name='testForm' action="<c:url value='/tests/add' />" method='POST'>
		<table><tr>
		<td>
			<button type="button" class="btn-lg btn-primary pull-left" onClick="create_new_test()"><spring:message code='button.createtest'/></button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</td>
		<td>
			<input name="title" id="title" type="text" class="form-control" />
			<div id="warning" class=""></div>
		</td>
		</tr></table>
		<input type="hidden" name="categoryId" value="${category.categoryId}"/>
	</form>
	</sec:authorize>
	
	</div>
	<br><br>
	
	<table class="table table-bordered table-hover table-striped">
	<tr>
		<th><spring:message code="category.test.label"/></th>
		<th><spring:message code="category.mark.label"/></th>
		<td></td>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		<th></th>
		</sec:authorize>
	</tr>
	
	<c:forEach var="test" items="${tests}">
		<tr>
			<c:choose>
				<c:when test="${test.aveMark != -1}">
					<td>
						<a href="<c:url value='/stats/${test.testId}' />"><c:out value="${test.title}"/></a>
					</td>
					<td>
						<c:out value="${test.aveMark}"/>
					</td>
				</c:when>
				<c:otherwise>
					<td>
						<c:out value="${test.title}"/>
					</td>
					<td>-</td>
				</c:otherwise>
			</c:choose>			
						
			<td>
				<spring:url value="/tests/start" var="startTestActionUrl" />
				<form:form name="currentQuestionForm" modelAttribute="currentQuestionForm" action="${startTestActionUrl}" method='POST'>
				<input type="hidden" name="testId" value="${test.testId}"/>
				<input type="hidden" name="categoryId" value="${category.categoryId}"/>
				<input type="hidden" name="questionText" value="_"/>
				<input type="hidden" name="questionNumb" value="0" />	
				<input type="hidden" name="answer[0].answerText" value=" "/>
				<input type="hidden" id="answer0.answerId" name="answer[0].answerId" value="true" />
				<input type="hidden" name="answer[0].isCorrect" value="true" />
				<button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="button.starttest"/></button>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form:form>
			</td>
			
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<td>
				<spring:url value="/test" var="testDeleteActionUrl" />
				<form:form name="testDeleteForm" action="${testDeleteActionUrl}" method='GET'>
				<input type="hidden" name="action" value="delete"/>
				<input type="hidden" name="testId" value="${test.testId}"/>
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