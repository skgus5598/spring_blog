<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />        
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.wrap {	width: 1040px;	margin: auto; }
	.detail1{text-decoration: none; background-color: highlight; color:green}
	.detail2{text-decoration: none; background-color: black; color:yellow}
 a{text-decoration: none;}	
</style>
</head>
<body>
<fmt:requestEncoding value="utf-8"/>
<jsp:include page="../default/header.jsp"></jsp:include>
session : ${loginUser }
		
		
		<c:choose>
			<c:when test="${loginUser == null }">
			 	<script>
			 		alert("로그인 먼저 하세요 ")
			 		location.href="${contextPath}/member/login"
			 	</script>
			</c:when>			
			<c:otherwise>
				<div class="wrap">
					<h1>모든 회원 정보 </h1>
					<c:forEach var="dto" items="${dto }"><hr><br>												
							아이디 :  ${dto.id }<br>							
							비밀번호 : ${dto.pwd } <br>
							주소 : ${dto.addr }<br>						
 					<c:choose>
						<c:when test="${loginUser == dto.id }">
							<a class="detail2"  href="${contextPath }/member/memInfo?id=${dto.id}">정보 보기 </a>
						</c:when>
						<c:otherwise>
							<a class="detail1"  href="${contextPath }/member/memInfo?id=${dto.id}">정보 보기 </a>
						</c:otherwise>
					</c:choose>															
					</c:forEach>
				</div>			
			</c:otherwise>
		</c:choose>
		
		
		
<jsp:include page="../default/footer.jsp"></jsp:include>
</body>
</html>