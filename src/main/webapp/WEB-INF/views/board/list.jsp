<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script>
	$(function() {
		$("#btnWrite").click(function() {
			location.href = "${path}/board/write.do";
		})
	})
	function list(page) {
		location.href = "${path}/board/list.do?curPage=" + page;
	}
</script>
</head>
<body>
	<%@ include file="../include/menu.jsp"%>
	<h2>게시판</h2>
	<button type="button" id="btnWrite">글쓰기</button>
	${map.count }개의 게시물이 있습니다.
	<table border="1" width="600px">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>이름</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${map.list }" var="row">
			<tr>
				<td>${row.bno }</td>
				<td><a href="${path}/board/view.do?bno=${row.bno}">
						${row.title }</a></td>
				<td>${row.name }</td>
				<td><fmt:formatDate value="${row.regdate }"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${row.viewcnt}</td>
			</tr>
		</c:forEach>
		<!-- 페이지 네비게이션 출력 -->
		<tr>
			<td colspan="5" align="center"><c:if
					test="${map.pager.curBlock > 1 }">
					<a href="${path}/board/list.do?curPage=1">[처음]</a>
				</c:if> <c:if test="${map.pager.curBlock > 1 }">
					<a href="${path}/board/list.do?curPage=${map.pager.prevPage}">[이전]</a>
				</c:if> <c:forEach var="num" begin="${map.pager.blockBegin}"
					end="${map.pager.blockEnd}">
					<c:choose>
						<c:when test="${num == map.pager.curPage}">
							<!-- 현재 페이지인 경우 하이퍼링크 제거 -->
							<span style="color: red;">${num}</span>
						</c:when>
						<c:otherwise>
							<a href="${path}/board/list.do?curPage=${num}">${num}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach> <c:if test="${map.pager.curBlock < map.pager.totBlock}">
					<a href="${path}/board/list.do?curPage=${map.pager.nextPage}">[다음]</a>
				</c:if> <c:if test="${map.pager.curBlock < map.pager.totBlock}">
					<a href="${path}/board/list.do?curPage=${map.pager.totPage}">[끝]</a>
				</c:if></td>
		</tr>
	</table>

</body>
</html>