<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Online quizes and tests</title>

<spring:url value="/resources/css/hello.css" var="coreCss" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
</head>

<nav class="navbar navbar-inverse ">
	<div class="container">
		<ul class="nav navbar-nav navbar-left">
		
			<li class="active"><a href='<spring:url value="/category" />'>Home</a></li>
        	<li class="active"><a href="<spring:url value="/register" />">Register</a></li>
		
		</ul>

		<div class="navbar-header" style="padding-left:200px;">
			<a class="navbar-brand" href="#" onclick="document.languageForm.locale.value='en';document.languageForm.submit();"><spring:message code="language.english"/></a>			 
			<a class="navbar-brand" href="#"  onclick="document.languageForm.locale.value='ru';document.languageForm.submit();"><spring:message code="language.russian"/></a>
			<a class="navbar-brand" href="#" onclick="document.languageForm.locale.value='ua';document.languageForm.submit();"><spring:message code="language.ukrainian"/></a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active">
					<c:if test="${pageContext.request.userPrincipal.name != null}">
					<c:url value="/logout" var="logoutUrl" />
					<form action="${logoutUrl}" method="post" id="logoutForm">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
					<a href="javascript:formSubmit()">Logout</a>
					</c:if>
				</li>
			</ul>
		</div>
	</div>
</nav>

<form name="languageForm" method='GET'>
<input type="hidden" name="locale" value=""/>				
</form>