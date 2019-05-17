<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>글목록</title>
</head>
<body>
	<p>전체${totalCount}건</p>
	<form action="./app/Articles">
		<input type="number" name="page" value="${param.page }" placeholder="페이지"
			min="1" max="${totalCount / 100 + 1 }" step="1" style="width: 70px;">
		<button type="submit">조회</button>
	</form>
	<table>
		<thead>
			<tr>
			    <td>글번호</td>
				<td>학번</td>
				<td>제목</td>
				<td>이름</td>
				<td>글내용</td>
				<td>날짜</td>
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