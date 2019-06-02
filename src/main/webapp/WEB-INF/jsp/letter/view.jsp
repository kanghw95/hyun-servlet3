<!--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>-->
<!doctype html>

<html>
<head>
<base href="${pageContext.request.contextPath}/" />
<title>편지 조회</title>
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
		<tbody>
			<tr><td>제목</td><td>${LETTER.title}</td></tr>
			<tr><td>번호</td><td>${LETTER.letterId}</td></tr>
			<tr><td>보낸사람</td><td>${LETTER.senderName}</td></tr>
			<tr><td>받는사람</td><td>${LETTER.receiverName}</td></tr>
			<tr><td>글내용</td><td>${LETTER.contentHtml}</td></tr>
			<tr><td>시간</td><td>${LETTER.cdate}</td></tr>
		</tbody>
	</table>
				<c:if test="${LETTER.senderId==sessionScope.MEMBER.memberId || LETTER.receiverId==sessionScope.MEMBER.memberId}">
				<span><a href="./app/letter/delete?letterId=${LETTER.letterId}"onclick="return confirmDelete();">삭제하기</a></span> | 
				</c:if>
		<p>
			<button type="submit" onclick="history.back()">돌아가기</button>
</p>

</body>
</html>