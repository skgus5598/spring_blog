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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>

.right-one{padding-left: 15%; padding-top: 20px;}
.right-table td,th{padding-top: 15px; padding-bottom: 15px; 
	padding-left: 10px; padding-right: 10px;}
.right-table td{ border-bottom: 1px dotted black; }
.backBtn{
			margin-top:20px;
			padding: 7px 25px; /*좌우 / 위아래 */
			border-radius: 5px;
			background-color: black; color: white;
			text-align:center;	
			border-color:white;
	}	
	.button {
			margin-top:20px;
			padding: 7px 25px; /*좌우 / 위아래 */
			border-radius: 5px;
			background-color: white; color: black;
			text-align:center;	
			border-color:white;
		}

	.replyBtn{
			margin-top:20px;
			padding: 7px 25px; /*좌우 / 위아래 */
			border-radius: 5px;
			background-color: black; color: white;
			text-align:center;	
			border-color:white;
	}
</style>
<script type="text/javascript">
   function readURL(input) {
      var file = input.files[0] //파일에 대한 정보
      console.log(file)
      if (file != '') {
         var reader = new FileReader();
         reader.readAsDataURL(file); //파일의 정보를 토대로 파일을 읽고 
         reader.onload = function (e) { // 파일 로드한 값을 표현한다
          //e : 이벤트 안에 result값이 파일의 정보를 가지고 있다.
           $('#preview').attr('src', e.target.result);
          }
      }
  }  
</script>
</head>
<body>
<fmt:requestEncoding value="utf-8"/>
<jsp:include page="../default/header.jsp"></jsp:include>
<c:set var="d" value="${personalData }"/>

	<div class="wrap">
		<div class="right-one">		
			<table border='1' class="right-table" >
			     <tr>
					<td>No.</td>
					<td>
						<input style="border: white;" name="id" value="${d.id }" readonly>
					</td>
				</tr>
				<tr>	<td>Clicked</td><td>not yet</td></tr>
				<tr>	<td>User</td><td>${d.id }</td></tr>
				<tr>
					<td>Title</td><td>${d.title }</td>
				</tr>
				<tr>
					<td>Content</td><td rowspan="2">${d.content }</td>
				</tr>
				<tr>	
					<c:if test="${ d.imageFileName == 'nan' }">
					<td><b>이미지가 없습니다</b></td>
					 </c:if>
					 <c:if test="${ d.imageFileName != 'nan' }">							      
					<td> <img width="200px" height="100px" src="${contextPath}/board/download?imageFileName=${d.imageFileName}"></td>		           
					 </c:if>					
				</tr>				
				<tr>
					<td colspan="2">
						<label>Comment</label><br>
						<textarea rows="5" cols="60" name="replyContent"  placeholder="댓글을 남겨보세요"></textarea>
					</td>						
				</tr>				
				<tr>
					<td colspan="2">
						<input class="backBtn" type="button" value="Back" onclick="location.href='${contextPath}/board/boardAllList'">			
						<c:if test="${loginUser == d.id }">						
							<input class="button" type="submit" value="Modify" onclick="location.href='${contextPath}/board/modify_form?writeNo=${d.writeNo }'" name="btn1" >
							<input class="button" type="submit" value="Delete" onclick="javascript: form.action='modify_delete.jsp'" name="btn2"  >
						</c:if>						
						<input class="replyBtn" type="submit" value="Reply" onclick="javascript: form.action='reply.jsp'">
					</td>
				</tr>						
	<%-- 
				<c:forEach var="replyDto" items="${dao.replyList() }">
			  		<c:if test="${d.id == replyDto.boardId }" >
						<tr>
						 	<td colspan="2">
								<label>작성자 : ${replyDto.userId }&nbsp;&nbsp; ${replyDto.savedate }</label><br>
								<textarea rows="3" cols="60" >${replyDto.replyContent }</textarea>
							</td>	
						</tr>
					</c:if>	
				</c:forEach>					
		--%>		
										
		</table>
		</div>
</div>
<jsp:include page="../default/footer.jsp"></jsp:include>
</body>
</html>