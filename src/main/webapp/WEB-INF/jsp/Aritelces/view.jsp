<!--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>-->
<!doctype html>

<html>
<head>
<base href="${pageContext.request.contextPath}/" />
<title>게시글 조회</title>
<script type="text/javascript">
	function confirmDelete() {
		if (confirm("삭제하시겠습니까?"))
			return true;
		else
			return false;
	}
</script>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
</head>
	<table>
		<thead>
			<tr><td>제목</td><td>${article.title}</td></tr>
			<tr><td>번호</td><td>${article.userId}</td></tr>
			<tr><td>이름</td><td>${article.name}</td></tr>
			<tr><td>날짜</td><td>${article.udate}</td></tr>
			<tr><td>글내용</td><td>${article.contentHtml}</td></tr>
		
		</thead>
	</table>
			<!-- 로그인 여부에 따라 분기 -->
				<c:if test="${article.userId == sessionScope.MEMBER.memberId }">
				<span><a href="./app/Aritelces/updp?articleId=${article.articleId}">수정하기</a></span> | 
				<span><a href="./app/Aritelces/deleteArticle?articleId=${article.articleId}"
								onclick="return confirmDelete();">삭제하기</a></span> 
				</c:if>
<p>
		<a href="./app/Articles">글목록</a>
</p>

</body>
</html>