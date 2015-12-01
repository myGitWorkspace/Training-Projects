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

	<h1><spring:message code="statistics.title"/> "${test.title}" ( "${test.category.name}" )</h1>
	<br />
	
	<div>
	<a href="<c:url value='/category' />"><spring:message code="breadcrumbs.allcategories"/></a> > 
	<a href="<c:url value='/category/${test.category.categoryId}' />"><c:out value="${test.category.name}"/></a> > 
	<c:out value="${test.title}"/>
	</div>
	
	<br><br>
		
	<table class="table table-bordered table-hover table-striped">
	<tr>
		<th><spring:message code="statistics.date.label"/></th>
		<th><spring:message code="statistics.correct.label"/></th>
		<th><spring:message code="statistics.wrong.label"/></th>
		<th><spring:message code="statistics.total.label"/></th>
		<th><spring:message code="statistics.progress.label"/></th>		
	</tr>
	
	<c:forEach var="statsItem" items="${statisticsList}">
		<tr>
			<td><c:out value="${statsItem.date}"/></td>
			<td><c:out value="${statsItem.correctAnswers}"/></td>
			<td><c:out value="${statsItem.wrongAnswers}"/></td>
			<td><c:out value="${statsItem.correctAnswers+statsItem.wrongAnswers}"/></td>
			<td>
			<fmt:formatNumber var="progress"  value="${statsItem.correctAnswers*100/(statsItem.correctAnswers+statsItem.wrongAnswers)}" maxFractionDigits="0" />  
  			<c:out value="${progress}"/>
  			</td>
		</tr>
	</c:forEach>	
	
	</table>
	
	<spring:url value="/category/${test.category.categoryId}" var="backToCatActionUrl" />
	<form:form name='backToCatForm' action="${backToCatActionUrl}" method='GET'>				
		<button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="button.back"/></button>				
	</form:form>

</div>

<jsp:include page="block/footer.jsp" />

</body>
</html>