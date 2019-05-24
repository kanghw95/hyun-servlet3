<!--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>-->
<!doctype html>

<html>
<head>
<base href="${pageContext.request.contextPath}/" />
<title>게시글 조회</title>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
		<!-- 로그인 여부에 따라 분기 -->
		<c:choose>
			<c:when test="${!empty sessionScope.MEMBER }">
				<!-- 로그인 했을 경우 -->
				<c:if test="${!empty sessionScope.ARTICLEID }">
		<p style="color: red;">본인 글이 아닙니다..</p>
	          </c:if>
				<span><a href="./app/update.jsp">수정</a></span> | 
				<span><a href="./app/delete.jsp">삭제</a></span> 
			</c:when>
			<c:otherwise>
				<!-- 로그인 하지 않았을 경우 -->
				<span><a href="./app/loginForm">로그인</a></span> |
				<span><a href="./app/register/step1">회원가입</a></span>
			</c:otherwise>
		</c:choose>
</head>
	<table>
		<thead>
			<tr><td>제목</td><td>${article.title}</td></tr>
			<tr><td>이름</td><td>${article.name}</td></tr>
			<tr><td>날짜</td><td>${article.udate}</td></tr>
			<tr><td>글내용</td><td>${article.contentHtml}</td></tr>
		</thead>
	</table>
	<form action="./app/main" method="post">
		<button type="submit">초기화면</button>
	</form>

</body>
</html>