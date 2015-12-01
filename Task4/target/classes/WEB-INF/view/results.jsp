<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<jsp:include page="block/header.jsp" />

<script>
var localeElems = document.getElementsByClassName("navbar-brand");
for(i=0; i<localeElems.length; i++) {	
	if(localeElems[i].tagName == "A")
		localeElems[i].style.visibility="hidden";
}
</script>

<div class="container">

	<h1><spring:message code="results.title"/> "${testTitle}"</h1>
	<h2><spring:message code="results.progress"/> "${progressResult}%"</h2>
	<h2><spring:message code="results.correct"/> "${numberCorrectAnswers}"</h2>
	<h2><spring:message code="results.wrong"/> "${numberWrongAnswers}"</h2>
	<h2><spring:message code="results.total"/> "${totalNumberQuestions}"</h2>	
	<br />
	
	<spring:url value="/category/${categoryId}" var="backToCategoryActionUrl" />
	
	<form:form name='backToCategoryForm' action="${backToCategoryActionUrl}" method='GET'>
	<button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="button.ok"/></button>	
	</form:form>


</div>

<jsp:include page="block/footer.jsp" />

</body>
</html>