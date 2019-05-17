<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>게시글</title>
</head>
<body>
	<h2>게시글 작성</h2>
	<form action="./app/Aritelces/good" method="post">
		<p>제목 :</p>
		<p>
			<input type="text" name="title" maxlength="100" style="width: 100%;"
				required>
</p>
		<p>내용 :</p>
		<p>
			<textarea name="content" style="width: 100%; height: 200px;" required></textarea>
       </p>
	
		<button type="submit">작성완료</button>
	</form>
</body>
</html>