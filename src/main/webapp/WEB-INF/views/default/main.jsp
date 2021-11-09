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
	.main {width:100%; height:700px;background-color: rgba(201, 10, 255, .9);}
	.main1{height:55%; display:flex; width:1100px;	}
	.button {
		border-radius: 15px;
		margin-top:20px;
		margin-left: 120px; 
		padding: 10px 30px; /*좌우 / 위아래 */
		border:white;
		background-color: white; color: black;
	}
	.button1{
		border-radius: 15px;
		padding: 10px 30px;  /*좌우 / 위아래 */
		margin-top:20px;
		margin-left:15px;	
		border:white;
		background-color:white; color: black; }	
	.button1:active{background-color: rgb(255,200,255); }		
	.button:hover{padding:13px 33px;;}
	.button:active{background-color: rgb(255,200,255);  }
	.main1-left{ width:50%; padding: 100px 80px 80px 80px; }
	.main1-left h2{ text-align: center; text-shadow: 10px 10px 15px purple; color:white;}	
	.main1-right{	padding-top:50px; width:50%; }
	
	.bigmain{display:flex; height:45% }
	.main2{background-color: rgba(255, 236, 0, 0.9); width:33.33%; }
	.main2 h3{background-color: rgba(80,40,255, 0.8); 
		margin-top: 40px;
		text-align: center;
		color:white;
	}
	.main2 img{ width:100%;		}
	.main3{background-color: rgba(29, 210, 189, 0.9); width:33.33%;}
	.main3 h2{
		padding: 10px;
		padding-top: 50px;
		padding-bottom: 30px;
		text-align: left;
		text-shadow:10px 10px 15px purple;
	}
	.main3 h3{
		color:white;
		padding: 15px;
		text-align: left;
	}

	.main4{background-color: rgba(255,65,156, 0.9) ;  width:33.33%; 
	display:flex;}
	.main4 ul { text-align:left; padding-left: 0; color:white;
		text-shadow:10px 10px 15px purple;
		padding-top: 30px;
	}

	.main4 img{ padding-top: 80px; padding-right: 20px;}
	.main li{ list-style: none;	 padding-left:20px; padding-right: 30px;}
 	.main li a{text-decoration: none; color:green;}

</style>

</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="wrap">
	<div class="main">
			<div class="main1">
			<div class="main1-left">
				<h2 >Welcome to Raina's First Website<br></h2>
				<h2>Now let's begin!</h2>
				<input class="button" type="button" value="Explore">
			</div>
			<div class="main1-right"><img src="../img/color.png"></div>			
			</div>
			<div class="bigmain">
				<div class="main2">
					<img src="../img/lip.png">	
					<h3>First First Web Page !</h3>							
				</div>
				<div class="main3">
					<h2> Hi , This is Raina</h2>
					<h3>in-depth guidess for making, marketing, and measuring videos.
						Get inspired and get shooting.</h3>							
					<input class="button1" type="button" value="Explore">					
				</div>
				<div class="main4">
					<ul>
						<li>in-depth guidess for making, marketing</li>
						<input class="button1" type="button" value="Explore">					
					</ul>
					<img src="../img/pink.png">
				</div>	
			</div>	
		</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>