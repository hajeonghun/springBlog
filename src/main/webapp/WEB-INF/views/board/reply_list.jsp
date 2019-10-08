<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<table style="width:700px">
<c:forEach var="row" items="${list}">
	<c:set var = "str" value="${fn:replace(row.replytext,'  ','&nbsp;&nbsp;')}" /> 
	<tr>
		<td>${row.name}
		 ( <fmt:formatDate value="${row.regdate }"
		 pattern="yyyy-MM-dd a HH:mm:ss" /> ) <br>
		<%-- ${row.replytext} --%>		
		${str}
		</td>
	</tr>
</c:forEach>

</table>s
</body>
</html>