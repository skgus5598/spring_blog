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
	.button {
			margin-top:20px;
			padding: 7px 25px; /*좌우 / 위아래 */
			border-radius: 15px;
			background-color: black; color: white;
			text-align:center;	
			border-color:white;
		}
	.button:hover{background-color: rgb(255,200,255); }
	.detail1{text-decoration: none; background-color: highlight; color:green}
	.detail2{text-decoration: none; background-color: black; color:yellow}
 a{text-decoration: none;}	
</style>
</head>
<body>
<fmt:requestEncoding value="utf-8"/>
<jsp:include page="../default/header.jsp"></jsp:include>
	<div class="wrap">
	<form action="modifyMem" method="post">	
		<h1> ${dto.id } 님의 정보 수정</h1><hr> <br>
		아이디 : ${dto.id }<input type="hidden" name="id" value="${dto.id }"><br>
		비밀번호 : <input type="text" name="pwd" placeholder="${dto.pwd }"><br>
		주소 : <input type="text" name="addr" placeholder="${dto.addr }"><br>
	<hr>
	<input class="button" type="button" value="취소하기 " onclick="location.href='memberList'">
	<input class="button" type="submit" value="수정하기">
	</form>	
	
	</div>

<jsp:include page="../default/footer.jsp"></jsp:include>
</body>
</html>