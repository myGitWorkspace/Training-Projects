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

	<h1><spring:message code="question.show.title"/> ${currentQuestionId} of ${totalNumberQuestions}</h1>
	<br />
	
	<spring:url value="/tests/start" var="startTestActionUrl" />
	
	<form:form name='currentQuestionForm' modelAttribute="currentQuestionForm" action="${startTestActionUrl}" method='POST'>

	<table class="table table-bordered table-hover table-striped">
	<tr>
		<th></th>
		<th>
		<div id="questionText">${currentQuestionForm.questionText}</div>
		<form:hidden path="questionText" value="${currentQuestionForm.questionText}"/>
		<br>
		<form:errors path="questionText" cssStyle="color: red;"/>
		</th>		
	</tr>
	
	<c:forEach var="answer" items="${currentQuestionForm.answer}"  varStatus="status">
		<tr>
			<td>
			<form:hidden path="answer[${status.index}].answerId" />
				<form:checkbox path="answer[${status.index}].isCorrect" id="answer[${status.index}].isCorrect" />
			</td>
			<td>
			<form:label path="answer[${status.index}].answerText" id="answer[${status.index}].answerText">${answer.answerText}</form:label>
			<form:hidden path="answer[${status.index}].answerText" value="${answer.answerText}"/>
			</td>
		</tr>
	</c:forEach>

	</table>
	
	<button type="submit" class="btn-lg btn-primary pull-right"><spring:message code="question.show.button.nextquestion"/></button>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<input type="hidden" name="questionNumb" value="${currentQuestionId}" />
	<input type="hidden" name="categoryId" value="${categoryId}" />
	</form:form>


</div>

<jsp:include page="block/footer.jsp" />

<script>
var elem=document.getElementById("questionText");
var text=document.getElementById("questionText").innerHTML;
elem.innerHTML = text.replace(/(\r\n|\n|\r)/gm, "<br>");
var numb = ${currentQuestionForm.answer.size()};
for(i=0; i<numb; i++) {
	answer=document.getElementById("answer["+i+"].answerText");
	answerText=document.getElementById("answer["+i+"].answerText").innerHTML;
	answer.innerHTML = answerText.replace(/(\r\n|\n|\r)/gm, "<br>");
}
</script>

</body>
</html>
