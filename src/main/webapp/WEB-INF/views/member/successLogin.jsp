<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>   
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath }" />        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<fmt:requestEncoding value="utf-8"/>
	<c:choose>
		<c:when test="${result == 1}">
			<script type="text/javascript">
				alert("존재하지 않는 아이디 입니다.");
				location.href="${contextPath}/login";
			</script>
		</c:when>
		<c:when test="${result ==2 }">
			<script type="text/javascript">
				alert("비밀번호가 틀렸습니다.");
				location.href="${contextPath}/login";
			</script>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				alert("로그인 성공!");				
				location.href="${contextPath}/successLogin?id=${id}";
			</script>
		</c:otherwise>
	</c:choose>


</body>
</html>