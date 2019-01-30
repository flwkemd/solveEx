<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

    </c:if>

	<div class="main-top-layout">
		<div class="container">
			<h1>키워드 검색</h1>
			<div class="row">
				<div class="col-12">
					<form id="search_form" name="search_form" onsubmit="return submitSearch();">
						<input type="hidden" name="page" value="1">
						<input type="hidden" name="page" value="${placePage.number}">
					    <input type="hidden" name="size" value="${placePage.size }">
						<div class="form-row">
							<div class="col">
								<div class="form-group">
									<label for="select-category">카테고리 </label>
									<select id="select-category" class="form-control" name="category">
										<c:forEach var="c" items="${EnumCategory }">
											<option value="${c.code }">${c.desc}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<label for="input-searchword">검색어</label>
									<input id="input-searchword" class="form-control" name="searchWord" placeholder="검색어를 입력하세요.">
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<label>&nbsp;</label>
									<div><button class="btn btn-primary" type="submit">검색</button></div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<hr>
		<h2>키워드 검색 리스트</h2>
		<div class="row">
			<div class="col-12">
				<div id="place">
					<ul class="list-group">
					</ul>
					<div id="resultMessage" class="alert" role="alert" style="display: none;">검색된 장소가 없습니다.</div>
				</div>
			</div>
		</div>
		<hr>
	</div>
	
</div>
	<script type="text/javascript">
		function submitSearch(page) {
			var pg = page ? page : 1;
			var frm = document.search_form;
			frm.page.value = pg;
			if (frm.searchWord.value == "") {
				alert("검색어를 입력하세요. ");
			} else {
				$
						.ajax({
							url : "/ajax/placeSearch",
							data : $(frm).serialize(),
							beforeSend: function(){
								$(frm).find("button[type=submit]").prop("disabled", true);
								$(frm).find("button[type=submit]").text("Searching for ...");
								console.log($(frm).find("button[type=submit]"));
							},
							success : function(res) {
								if (res.meta.total_count < 1) {
									$("#place > ul").html("");
									$("#place > #resultMessage").show();
								} else {
									$("#place > #resultMessage").hide();
									var html = "";
									$(res.documents)
											.each(
													function(idx) {
														var category_group_name = "", phone = "", place_name = "";
														$(this.category_group_name)
																.each(
																		function() {
																			category_group_name += (this + " ")
																		});
														$(this.phone)
																.each(
																		function() {
																			phone += (this + " ")
																		});
														var arr_place_name = this.place_name.split(" ");
														if(arr_place_name.length>1){
															place_name = arr_place_name[1];
														}else{
															place_name = arr_place_name[0];
														}

														html += "<li class='list-group-item'>";
														html += "<dl><dt><a href='./detail?place_name="
																+ place_name
																+ "'>"
																+ this.place_name
																+ " | "
																+ this.road_address_name
																+ "</a>"
																+" <button type='button' class='btn btn-primary btn-bookmark add' data-isbn='"+place_name+"'>북마크</button>"
																+"</dt>";
														html += "<dd><div class='left'>"
																+ place_name
																+ "</div><div class='right'>전화번호: "
																+ phone
																+ "<br> 카테고리: "
																+ category_group_name
																+ "</div></dd></dl></li>";
													});
									if (!res.meta.is_end) {
										html += "<li><button class='btn btn-primary btn-lg btn-block' onclick='submitSearch("
												+ (pg + 1)
												+ "); $(this).parent().remove();'>더보기 </button></li>";
									}
									if (pg > 1) {
										$("#place > ul").append(html);
									} else {
										$("#place > ul").html(html);
									}
								}
								console.log(res);
							},
							error : function(res) {
								console.log(res);
								alert(res);
							},
							complete : function() {
								$(frm).find("button[type=submit]").prop("disabled", false);
								$(frm).find("button[type=submit]").text("검색");
							}
						});
			}

			return false;
		}

		/* $(document).ready(function() {
			<c:if test="${not empty placePage.content }">
			$('.paging-layout').bootpag({
			    total: ${placePage.totalPages},
			    page: ${placePage.number+1},
			    maxVisible: 10,
			    leaps: true,
			    firstLastUse: true,
			    first: '←',
			    last: '→',
			    wrapClass: 'pagination',
			    activeClass: 'active',
			    disabledClass: 'disabled',
			    nextClass: 'next',
			    prevClass: 'prev',
			    lastClass: 'last',
			    firstClass: 'first'
			}).on("page", function(event, num){
				console.log(num);
				var frm = document.searchForm
				frm.page.value = num-1;
				frm.submit();
			});
			</c:if>
		}); */

		
	</script>


<!-- /container -->
<script type="text/javascript" src="${contextPath}/resources/js/jquery.paging.min.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
