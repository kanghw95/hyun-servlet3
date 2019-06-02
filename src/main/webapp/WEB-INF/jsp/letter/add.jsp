<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>편지 쓰기</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<h2>편지 쓰기</h2>
	<p>
		<a href="./app/members">회원 목록</a>
	</p>
	<form action="./app/letter/add" method="post">
		<p>받는 사람 및 회원 번호</p>
		<p>
		<input type="text" name="receiverName" value="${param.receiverName }" required autofocus> 
		<input type="text" name="receiverId" value="${param.receiverId }" required autofocus>
		</p>
		<p>제목 :</p>
		<p>
			<input type="text" name="title" value="${param.title }" required autofocus>
		</p>
		<p>내용 :</p>
		<p>
			<textarea name="content" style="width: 100%; height: 200px;" required></textarea>
		</p>
		<p>
			<button type="submit">add</button>
		</p>
	</form>
</body>
</html>