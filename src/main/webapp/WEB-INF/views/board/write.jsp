<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="../include/header.jsp"%>
<script src="${path}/ckeditor/ckeditor.js" ></script>
<script>
	$(function() {
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
	<h2>글쓰기</h2>
	<form id="form1" name="form1" method="post"
		action="${path}/board/insert.do">
		<div>
			제목 <input name="title" id="title" size="80" placeholder="제목을 입력하세요">
		</div>
		<div style="width: 800px;">
			내용
			<textarea id="content" name="content" rows="3" cols="80"
				placeholder="내용을 입력하세요"></textarea>
			<script>
				CKEDITOR.replace("content");
			</script>
		</div>
		<div>
			첨부파일을 등록하세요
			<div class="fileDrop"></div>
			<div id="uploadedList"></div>
		</div>
		<div style="width: 700px; text-align: center;">
			<button type="button" id="btnSave">확인</button>
		</div>

	</form>

</body>
</html>