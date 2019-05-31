<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>글목록</title>
<style type="text/css">
table {
	margin-top: 10px;
	border-collapse: collapse;
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
	width: 80%;
}
th, td {
	padding: 7px 0;
}
th {
	border-bottom: 1px solid gray;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<p>전체${totalCount}건</p>
	<form action="./app/Articles">
		<input type="number" name="page" value="${param.page }" placeholder="페이지"
			min="1" max="${totalCount / 100 + 1 }" step="1" style="width: 80px;">
	<p>
		<a href="./app/Aritelces/addform">[글쓰기]</a>
	</p>
		<button type="submit">조회</button>
	</form>
	<table>
		<thead>
			<tr>
				   	<th>글번호</th>
					<th>회원번호</th>
					<th>제목</th>
					<th>이름</th>
					<th>글내용</th>
					<th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${Articles}">
				<tr>
				     	<td>${article.articleId}</td>
						<td>${article.userId}</td>
						<td><a href="./app/Aritelces/view?articleId=${article.articleId}">${article.title}</a></td>
						<td>${article.name}</td>
						<td>${article.contentHtml}</td>
						<td>${article.udate}</td> 
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</head>
</html>