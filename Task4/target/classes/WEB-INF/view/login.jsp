<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>

<jsp:include page="block/header.jsp" />
<head>
<style>

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}
.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}
.form-signin .checkbox {
	font-weight: normal;
}
.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}
.form-signin .form-control:focus {
	z-index: 2;
}
.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

</style>
</head>
<body >
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>	
	
	<form class="form-signin" role="form" name='loginForm' action="<c:url value='/login' />" method="POST">
	<h2 class="form-signin-heading"><spring:message code="login.title"/></h2>
	<input type="text" name="username" class="form-control" placeholder="Name" required autofocus> 
	<input type="password" name="password" class="form-control" placeholder="Password" required> 
	<button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="button.login"/></button>
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	
<jsp:include page="block/footer.jsp" />

</body>
</html>