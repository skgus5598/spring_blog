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
	.wrap {	width: 1040px;	margin: auto;}
	.empty{ height:50px; }	
	.bigMain img{ height:700px; opacity:0.5; }
	.bigMain a{text-decoration: none;}
	.bigMain a:hover{transition: all0.25s; opacity:0.4;}
	.mainFirst{width:50%;  flex-flow: column;  border-radius: 5px; background-color:rgba(0,0,86,0.9);
		text-align:center; }
	.mainFirst-a input{ height:30px; border-radius: 5px; width:150px;  border-color: white;}
	.button {
			margin-top:20px;
			padding: 10px 30px; /*좌우 / 위아래 */
			border-radius: 15px;
			background-color: black; color: white;
			text-align:center;	
			border-color:white;
		}
	.button:hover{padding:13px 33px;;}
	.button:active{background-color: rgb(255,200,255);  }	
	.mainSec{ width:50%; text-align: center; border-radius: 5px; background-color:rgba(255,255,86,0.7); }
	.mainSec .button{margin-bottom: 10px;}
	.mainSec-a input{height:30px; border-radius: 5px; width:150px; margin-top: 15px;
		 border-color: white; }
		.topButton{margin-left: 1000px;
		border-radius: 15px;
		padding: 5px 25px;
		background-color: black; color: white;
		text-align:center;	
		border-color:white;
	}	 
</style>

</head>
<body>
<jsp:include page="../default/header.jsp"></jsp:include>
	<div class="wrap">
		<div class="empty">
		</div>
		<div class="bigMain">
			<table>			
				<tr>
					<td class="mainFirst">			
					<form action="chkUser" method="post">
						<div class="mainFirst-a">	
							<input type="text" name="id" placeholder="아이디"><br><br>
							<input type="password" name="pwd" placeholder="비밀번호">		
						</div>			
						<div><button type="submit" class="button">로그인</button></div>
					</form>
					</td>
					<td><a href="${contextPath }/register"><img src="../img/black.png"></a></td>
					<td class="mainSec">
					<form action="register" method="post">
						<div class="mainSec-a">								
							<input type="text"  name="id" placeholder="input id"><br><br>
							<input type="password" name="pwd" placeholder="input password"><br><br>				
							<input type="text" name="addr" placeholder="input address"><br><br>	
						</div>				
						<div><button type="submit" class="button">회원가입</button></div>
					</form>			
					</td>
				</tr>			
			</table>		
	</div>	
	</div>
<jsp:include page="../default/footer.jsp"></jsp:include>
</body>
</html>