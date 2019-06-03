<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<title>보낸목록</title>
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
	<table>
		<thead>
			<tr>
				   	<th>번호</th>
					<th>제목</th>
					<th>보낸사람</th>
					<th>받는사람</th>
					<th>날짜</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="LETTER" items="${listOfSender}">
				<tr>
				     	<td>${LETTER.letterId}</td>
						<td><a href="./app/letter/view?letterId=${LETTER.letterId}&mode=SENT">${LETTER.title}</a></td>
						<td>${LETTER.senderName}</td>
						<td>${LETTER.receiverName}</td>
						<td>${LETTER.cdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</head>
</html>