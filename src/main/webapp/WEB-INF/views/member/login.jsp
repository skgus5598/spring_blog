<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />        
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath }/resources/js/daumpost.js"></script><!-- js파일 절대경로를 넣어준다  -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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


<script>
/*	function daumPost(){
	    new daum.Postcode({
	        oncomplete: function(data) {
	        	// R : 도로명, J : 지번
	        	console.log("data.userSelectedType : "+ data.userSelectedType);
	        	console.log("data.roadAddress : "+data.roadAddress);
	        	console.log("data.jibunAddress : " + data.jibunAddress);
	        	console.log("data.zonecode" + data.zonecode);
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	            var addr=""
	            if(data.userSelectedType == 'R'){
	            	addr = data.roadAddress
	            }else{
	            	addr = data.jibunAddress
	            }
	            document.getElementById("addr1").value = data.zonecode
	            $("#addr2").val(addr)
	            $("#addr3").focus()	            
	        }
	    }).open();
	}
얘는 공통기능으로 빼놓는다, 회원수정에도 쓰이기 때문에!   */
	function register(){
		addr1 = $("#addr1").val()
		addr2 = $("#addr2").val()
		addr3 = $("#addr3").val()
		addr = addr1+"/"+addr2+"/"+addr3
		$("#addr1").val(addr) //addr1에 name값을 줬기 때문에 서버로 들어간다 
		fo.submit() // form의 id값으로 fo를 줬었고, fo를 submit하면 컨트롤러로 이동한다 
	}
	
</script>
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
						<div><button type="submit" class="button">로그인</button></div><br>
						<input type="checkbox" name="autoLogin">로그인 유지
					</form>
					</td>
					<td><a href="${contextPath }/member/register"><img src="../img/black.png"></a></td>
					<td class="mainSec">
					
					<form id="fo" action="register" method="post">
						<div class="mainSec-a">								
							<input type="text"  name="id" placeholder="input id"><br><br>
							<input type="password" name="pwd" placeholder="input password"><br><br>				
						<input type="text" name="addr" id="addr1" readonly placeholder="postcode">
						<input type="button" onclick="daumPost()" value="우편번호 찾기"><br>
						<input type="text"  id="addr2" readonly placeholder="주    소"><br>
						<input type="text"  id="addr3" placeholder="상세주소"><br>
							<br><br>	
						</div>				
						<div><button class="button" onclick="register()">회원가입</button></div><br>
						
					</form>			
					
					</td>
				</tr>			
			</table>		
	</div>	
	</div>
<jsp:include page="../default/footer.jsp"></jsp:include>
</body>
</html>