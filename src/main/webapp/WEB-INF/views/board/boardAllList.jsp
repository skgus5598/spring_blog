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
<style>
.wrap {	width: 1040px;	margin: auto;}
.button {
			margin-top:20px;
			padding: 7px 25px; /*좌우 / 위아래 */
			border-radius: 5px;
			background-color: black; color: white;
			text-align:center;	
			border-color:white;
		}
.button:hover{background-color: rgb(255,200,255); }

.right-one{padding-left: 15%; padding-top: 20px;}
.right-table td,th{padding-top: 15px; padding-bottom: 15px; 
	padding-left: 10px; padding-right: 10px;}
.right-table tr:hover{transition:all 0.15s; background-color: rgba(255,255,212,0.5);}
.right-table td{ border-bottom: 1px dotted black; }
.firstTr{background-color: rgb(255,255,212);}
a{text-decoration: none; color:green;}

.pages {text-align: center;}

</style>
</head>
<body>
<fmt:requestEncoding value="utf-8"/>
<jsp:include page="../default/header.jsp"></jsp:include>
<div class="wrap">
	<div class="right-one">			
			<table border='1' class="right-table" >
			      <tr class="firstTr">
			        <th>No.</th>
			        <th>User Id</th>			        
			        <th>Title</th>		
			        <th>Date</th>			
			        <th>Clicked</th>		
			        <th>Image_file_name</th>				
			      </tr>
			      <c:if test="${boardList.size() == 0}">
			      	<tr>
			      		<td colspan="6">저장 데이터 없음</td>
			      	</tr>
			      </c:if>     			      
			      <c:forEach var="dto" items="${boardList }">			      
				      <tr>
				        <td>${dto.writeNo }</td>
				        <td>${dto.id }</td>
				        <td> <%--contentView/${dto.writeNo} 이렇게 경로로 넘겨줄 수 도 있다 --%>
				        	<a  href="${contextPath }/board/contentView?writeNo=${dto.writeNo}">${dto.title }</a>
				        </td>	
				        <td>${dto.saveDate }</td>	        
				        <td>${dto.hit }</td>	 	
				        <td>${dto.imageFileName }</td>	   		          			          
				      </tr>
			     </c:forEach>
			     <tr>
			     	<td colspan="6">
			     		<div align="left">
			     			<c:forEach var="num" begin="1" end="${repeat }">
			     				<a href="boardAllList?${num }">[${num }]</a>			     			
			     			</c:forEach>
			     		</div>
			     	</td>
			     </tr>
			     
			     <tr>
			     	<td colspan="6"><a href="${contextPath }/board/writeForm">글작성</a>
			     </tr>
			     
			       <!--  여기 페이징처리!! 
			     <tr class="pages">
			     	<td colspan="8">
			     	<c:choose>
			     		<c:when test="${param.start == null }">
			     			<c:set var="start" value="1"/>
			     		</c:when>
			     		<c:otherwise>
			     			<c:set var="start" value="${param.start }"/>
			     		</c:otherwise>
			     	</c:choose>
			    	
			    	<c:choose>
			    		<c:when test="${start >1 }">
			    			<button onclick='location.href="boardList.jsp?start=${start -1}"'>이전</button>
			    		</c:when>
			    		<c:otherwise>
			    			<button disabled>이전</button>
			    		</c:otherwise>
			    	</c:choose>			    
			     	
				     <c:forEach var="cnt"  begin="1"  end="${pd.totEndPage }" step="1">
				     		<a href="boardList.jsp?start=${cnt }">[${cnt }]</a>			     
			     	  </c:forEach>
			     	  			     	
			     	  <c:choose>
			     	  <c:when test="${start < pd.totEndPage }">
			     	  		<button onclick="location.href='boardList.jsp?start=${start+1}'">다음</button>
			     	  </c:when>
			     	    <c:otherwise>
			     	    	<button disabled>다음</button>
			     	    </c:otherwise>
			     	  </c:choose>
			     	
			     	</td>
			     </tr>
			     
			     <tr>
			     	<c:choose>
			     		<c:when test="${loginUser != null }">
			     		<td colspan="8"><a class="button" href="writeForm.jsp" >Write From</a></td>
			     		</c:when>
			     		<c:otherwise>
			     		<td colspan="8"><a class="button" href="${contextPath }/default/loginPage.jsp">Login First</a></td>
			     		</c:otherwise>
			     	</c:choose>
			     </tr>
			  --> 
			     
			  </table>
			</div>	
</div>
<jsp:include page="../default/footer.jsp"></jsp:include>
</body>
</html>