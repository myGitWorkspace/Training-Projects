<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>

<style>
.row {
float:left;
clear:both;
border:solid grey 1px;
margin-bottom:17px;
padding:10px;
width:900px;
}

.column1 {
float:left;
width:100px;

}

.column2 {
float:left;
border:solid red 0px;
//padding-bottom:10px;
}

.checkbox {
float:left;
border:solid red 0px;
padding-left:15px;
}

.answer {
float:left;
width:700px;
border:solid green 0px;
}

.button1 {
float:left;
width:200px;

}

.button2 {
float:left;
width:200px;
padding-left:50px;
}

.clear {
clear:both;
}
</style>

<script>
var answernumber = 0;

function add_answer() {	
	
	answernumber = document.getElementsByClassName("answer").length;
	
	answernumber++;
	var newanswer = '<div class="column1">'+
	'Answer # '+answernumber+
	'</div>'+
	'<div class="column2">'+
	'<div class="answer" >'+
	'<textarea name="answer['+(answernumber-1)+'].answerText" rows="3" cols="70" class="form-control" ></textarea>'+
	'</div>'+
	'<div class="checkbox">'+
	'<label>'+
	'<input name="answer['+(answernumber-1)+'].isCorrect" type="checkbox"  value="true"/><input type="hidden" name="_answer['+(answernumber-1)+'].isCorrect" value="on"/>'+
	'</label>'+
	'</div>'+
	'</div>';
	
	var container=document.getElementById("answers");
	var newnode=document.createElement("div");
	var newnode_name = "row";
	newnode.setAttribute("class", newnode_name);	
	newnode.setAttribute("id", "answer_"+(answernumber-1));	
	newnode.innerHTML=newanswer;
	container.appendChild(newnode);
	
	if(answernumber == 3) {
		document.getElementById("delete_button").style.display = "block";
	}
	
}

function delete_answer() {

	if (answernumber == 2)
		return;

	var answers_div=document.getElementById("answers");

	// child node .........
	var answer_name = "answer_"+(answernumber-1);
	answer_elem = document.getElementById(answer_name);	
	
	// remove child node ........
	answers_div.removeChild(answer_elem);

	if(answernumber == 3) {
		document.getElementById("delete_button").style.display = "none";
	}

	answernumber-=1;

	}
	
function next_question() {
	
	document.questionForm.submit();
}
	
	var leave_page = true;
	
	window.addEventListener("beforeunload", function (e) {
		  
		if(leave_page == true) {
			  var message = "Leave page or not",
			  e = e || window.event;
			  if (e) {
			    e.returnValue = message;
			  }	  
			  return message;			
		}

		});
	
</script>

<jsp:include page="block/header.jsp" />

<script>
var localeElems = document.getElementsByClassName("navbar-brand");
for(i=0; i<localeElems.length; i++) {	
	if(localeElems[i].tagName == "A")
		localeElems[i].style.visibility="hidden";
}
</script>

<div class="container" >

	<h1><spring:message code="question.add.title"/> ${currentQuestion} )</h1>
	<br />

	<spring:url value="/question/add" var="questionActionUrl" />

	<form:form name='questionForm' modelAttribute="questionForm" action="${questionActionUrl}" method='POST'>
		<div class="row">
		<div class="column1 form-group">
			<label for="question" class="col-sm-2 control-label">
				<spring:message code="question.add.question.label"/>
			</label>
		</div>
		<div class="column2 form-group">
			<form:textarea path="questionText" rows="7" cols="70" cssClass="form-control" id="questionText" />
			<form:errors path="questionText" cssStyle="color: red;"/>
		</div>
		</div>
		
		<br><br>
		
		<div id="answers">
		<c:forEach items="${questionForm.answer}" var="answer" varStatus="status">
		<div class="row">
		<div class="column1 form-group">
			<label for="answer" class="col-sm-2 control-label">
			<spring:message code="question.add.answer.label"/><c:out value='${status.index+1}' />
			</label>
		</div>
		<div class="column2 form-group">
  			<div class="answer" > 
    			<form:textarea path="answer[${status.index}].answerText" rows="3" cols="70" cssClass="form-control"/>    
			</div>
  		<div class="checkbox form-group">
  			<label>
  				<form:checkbox path="answer[${status.index}].isCorrect" id="answer[${status.index}].isCorrect" />
  			</label>    
  		</div>
  		
		</div>
		</div>
		</c:forEach>
		</div>
 
 		<div class="clear"></div>
 		
 		<div class="row">
		<div class="button1">
			<button type="button" class="btn-lg btn-primary pull-left" onClick="add_answer()"><spring:message code="question.add.button.addanswer"/></button>
		</div>
		<div class="button2">
			<button type="button" class="btn-lg btn-primary pull-left" id="delete_button" onClick="delete_answer()"><spring:message code="question.add.button.deleteanswer"/></button>
		</div>		
		<div class="button2">
			<button type="button" class="btn-lg btn-primary pull-left" onClick="leave_page=false;next_question()"><spring:message code="question.add.button.addquestion"/></button>
		</div>
		<div class="button2">
			<spring:url value="/tests/save" var="testSaveActionUrl" />
			<button type="button" class="btn-lg btn-primary pull-left" onClick="leave_page=false;getElementById('questionForm').action='${testSaveActionUrl}';getElementById('questionForm').submit();"><spring:message code="question.add.button.savetest"/></button>
		</div>
		</div>
 		
 		<div class="clear"></div>
 		
 		<input type="hidden" name="questionNumb" value="${currentQuestion}" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

	</form:form>

</div>

<jsp:include page="block/footer.jsp" />

</body>
</html>