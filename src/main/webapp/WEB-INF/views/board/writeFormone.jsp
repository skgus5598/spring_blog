<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />        
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {margin: 0;}
.wrap {	width: 1040px;	margin: auto;}
.button {
			margin-top:20px;
			padding: 7px 25px; /*좌우 / 위아래 */
			border-radius: 5px;
			background-color: black; color: white;
			text-align:center;	
			border-color:white;
			 margin-left: 410px;
		}
.backBtn{
			margin-top:20px;
			padding: 7px 25px; /*좌우 / 위아래 */
			border-radius: 5px;
			background-color: black; color: white;
			text-align:center;	
			border-color:white;
	}		
.button:hover{background-color: rgb(255,200,255);}
.form {padding-left: 250px;; padding-top: 20px;}
</style>
</head>
<body>
<jsp:include page="../default/header.jsp"></jsp:include>
	<div class="wrap">
		<div class="form">
			<form action="${contextPath }/board/writeSave.jsp" method="post">
				<table>				
					<tr>
						<td>User</td>
						<td>
							<input type="text" name="name" value="${loginUser }" readonly>
						</td>
					</tr>
					<tr>
						<td>Title</td>
						<td>
							<textarea name="title" rows="2" cols="50" placeholder="제목을 적어주세요"></textarea>
						</td>
					</tr>
					<tr>
						<td>Content</td>
						<td>
							<textarea name="content" rows="20" cols="50" placeholder="내용을 입력하세요"></textarea>
						</td>
					</tr>
					<tr>					
						<td><input class="backBtn" type="button" value="back" onclick="location.href='${contextPath}/board/boardList.jsp'"></td>
						<td><input class="button" type="submit" value="done">	</td>
					</tr>				
				</table>				
			</form>
		</div>		
</div>
<jsp:include page="../default/footer.jsp"></jsp:include>
</body>
</html>