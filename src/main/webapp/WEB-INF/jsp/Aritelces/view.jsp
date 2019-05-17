<!--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>-->
<!doctype html>

<html>
<head>
<base href="${pageContext.request.contextPath}/" />
<title>게시글 조회</title>
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