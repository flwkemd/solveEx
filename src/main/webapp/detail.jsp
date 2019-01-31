<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>키워드 검색</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/style.css">
<style type="text/css">
body.loading .container .media {
	visibility: hidden;
}
</style>
</head>
<body class="loading">
	<div class="container mt-3">
			<div class="media-body">
				<p>
					장소명 : <span class="place-place_name"></span>
				</p>
				<p>
					카테고리 이름 : <span class="place-category_name"></span>
				</p>
				<p>
					전화번호 : <span class="place-phone"></span>
				</p>
				<p>
					주소 : <span class="place-address_name"></span>
				</p>
				<p>
					도로명 주소 : <span class="place-road_address_name"></span>
				</p>
				<p>
					x : <span class="place-x"></span>
				</p>
				<p>
					y  : <span class="place-y"></span>
				</p>
				<p>
					다음 지도 바로가기 : <a href=><span class="place-place_url"></span></a>
				</p>
			</div>
			
			<div id="map" style="width:500px;height:400px;"></div>
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9ec3ac48e69bf6523be0fab261bcf934"></script>
				<script>
					var container = document.getElementById('map');
					var options = {
						center: new daum.maps.LatLng(${y}, ${x}),
						level: 3
					};
			
					var map = new daum.maps.Map(container, options);
				</script>
						
		</div>

	<script type="text/javascript">

		function init() {
			$
					.ajax({
						url : "/ajax/getPlace/place_name/${param.place_name}",
						success : function(res) {
							if (res.meta.total_count > 0) {
								var doc = res.documents[0];
								var place_name = "", phone = "", address_name = "", place_url = "";
								$(doc.place_name).each(function() {
									place_name += (this + " ")
								});
								$(doc.phone).each(function() {
									phone += (this + " ")
								});
								$(".place-img").html(thumbnail);
								$(".place-title").text(doc.title);
								$(".place-place_name").text(doc.place_name);
								$(".place-category_name").text(doc.category_name);
								$(".place-phone").text(doc.phone);
								$(".place-address_name").text(doc.address_name);
								$(".place-road_address_name").text(doc.road_address_name);
								$(".place-x").text(doc.x);
								$(".place-y").text(doc.y);
								$(".place-place_url").text(doc.place_url);

								$("body").removeClass("loading");
							} else {
								alert("정상적인 접근이 아닙니다.");
								location.replace("/");
							}
							console.log(res);
						},
						error : function(res) {
							console.log(res);
							alert("잠시후 다시 시도해주세요.");
							location.replace("/");
						},
						conplete : function() {

						}
					});
		}

		$(document).ready(function() {
			init();
		});
	</script>
</body>
</html>