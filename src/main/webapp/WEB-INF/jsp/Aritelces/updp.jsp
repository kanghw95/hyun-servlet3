<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>글 수정</title>
</head>
<body>
	<h2>글 수정</h2>
	<form action="./app/Aritelces/upArticle?articleId=${article.articleId}" method="post">
	
		<p>제목 :</p>
		<p>
			<input type="text" name="title" required value="${aritcle.title}" >
		</p>
		<p>내용 :</p>
		<p>
			<textarea name="content" style="width: 100%; height: 200px;" required>${aritcle.content}</textarea>
		</p>
		<p>
			<button type="submit">완료</button>
		</p>
	</form>
</body>
</html>