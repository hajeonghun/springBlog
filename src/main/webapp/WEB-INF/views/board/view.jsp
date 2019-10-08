<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script src="${path}/ckeditor/ckeditor.js"></script>
<script>
	$(function() {
		//댓글 목록 출력	
		listReply();
		//댓글 쓰기
		$("#btnReply").click(function() {
			var replytext = $("#replytext").val(); //댓글 내용
			var bno = "${dto.bno}"; //게시물 번호
			var param = { //json방식
				"replytext" : replytext,
				"bno" : bno
			};
			$.ajax({
				type : "post",
				url : "${path}/reply/insert.do",
				data : JSON.stringify(param),
				contentType : "application/json; charset=utf-8",
				success : function() {
					console.log("success");
					alert("댓글이 등록되었습니다.");
					listReply();
				}
			})
		})
		$("#btnUpdate").click(function() {
			document.form1.action = "${path}/board/update.do";
			document.form1.submit();

		})
		$("#btnList").click(function() {
			location.href = "${path}/board/list.do";
		})
		$("#btnSave").click(function() {
			document.form1.submit();
		});
		$(".fileDrop").on("dragenter dragover", function(e) {
			e.preventDefault();
		})
		$(".fileDrop").on("drop", function(e) {
			event.preventDefault();
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			var formData = new FormData();
			formData.append("file", file);
			$.ajax({
				type : "post",
				url : "${path}/upload/uploadAjax",
				data : formData,
				dataType : "text",
				processData : false,
				contentType : false,
				success : function(data, status, req) {
					console.log("data:" + data);
					console.log("status:" + status);
					console.log("req:" + req.status);
				}
			});
		});
	});

	//댓글 목록 출력 함수
	function listReply() {
		$.ajax({
			type : "get",
			url : "${path}/reply/list.do?bno=${dto.bno}",
			success : function(result) {
				$("#listReply").html(result);
			}
		})
	}
</script>

<style>
.fileDrop {
	width: 600px;
	height: 100px;
	border: 1px dotted gray;
	background-color: gray;
}
</style>
</head>
<body>
	<%@ include file="../include/menu.jsp"%>
	<h2>게시물 보기</h2>
	<form id="form1" name="form1" method="post"
		action="${path}/board/insert.do">
		<div>
			제목 <input name="title" id="title" size="80" placeholder="제목을 입력하세요"
				value="${dto.title}">
		</div>
		<div>조회수 : ${dto.viewcnt}</div>
		<div style="width: 800px;">
			내용
			<textarea id="content" name="content" rows="3" cols="80"
				placeholder="내용을 입력하세요">${dto.content}</textarea>
			<script>
				CKEDITOR.replace("content", {
					height : "800px"
				});
			</script>
		</div>
		<div>
			첨부파일을 등록하세요
			<div class="fileDrop"></div>
			<div id="uploadedList"></div>
		</div>
		<div style="width: 700px; text-align: center;">
			<input type="hidden" name="bno" value="${dto.bno}">
			<c:if test="${sessionScope.userid == dto.writer}">
				<button type="button" id="btnUpdate">수정</button>
				<button type="button" id="btnDelete">삭제</button>
			</c:if>
			<button type="button" id="btnList">목록</button>
		</div>

	</form>
	<!-- 댓글 작성 -->
	<div style="width: 700px; text-align: center;">
		<c:if test="${sessionScope.userid != null }">
			<textarea rows="5" cols="80" id="replytext" placeholder="댓글을 작성하세요"></textarea>
			<br>
			<button type="button" id="btnReply">댓글쓰기</button>
		</c:if>
	</div>
	<!-- 댓글 목록 -->
	<div id="listReply"></div>
</body>
</html>