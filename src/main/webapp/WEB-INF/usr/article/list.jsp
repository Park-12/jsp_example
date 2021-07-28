<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP BOARD</title>
<!-- 모바일 축소 X -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 데이지 UI 불러오기 -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@0.20.0/dist/full.css"
	rel="stylesheet" type="text/css" />

<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/font.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resource/common.css" />
</head>
<body>
	<section class="section section-article-write px-4">
		<div class="container mx-auto">
			<div class="card bordered shadow-lg">
				<div class="card-title">
					<a href="javascript:history.back();" class="cursor-pointer">
						<i class="fas fa-chevron-left"></i>
					</a>
					<span>게시물 리스트</span>
				</div>
				<div class="px-4 py-4">
					<c:forEach items="${articles}" var="article">
						<div>
							번호 : ${article.id}
							<br>
							작성 : ${article.regDate}
							<br>
							갱신 : ${article.updateDate}
							<br>
							제목 : ${article.title}
							<br>
							내용 : ${article.body}
							<br>
						</div>
						<hr />
					</c:forEach>
				</div>
			</div>
	</section>
</body>
</html>