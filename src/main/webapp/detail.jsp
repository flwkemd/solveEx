<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>키워드 검색</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>  
<script type="text/javascript" src="${contextPath}/resources/js/jquery.bootpag.min.js"></script>  
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
					다음 지도 바로가기 : <a href='javascript:void(0);' onclick="func(); return false;" ><span id="url" class="place-place_url"></span></a>
				</p>
			</div>	
			<div id="map" style="width:500px;height:400px;"></div>	
			<div><button class="'btn btn-primary btn-sm btn-block'" onclick="goBack()">Go Back</button></div>					
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

		function func(){
				var place_url = $("#url").text();
				window.location.href=place_url;
			}

		$(document).ready(function() {
			init();
			var url = document.getElementById("url").text;
			console.log(url);

		});
	</script>


				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=79dce2c081dab1506bf17e230df5503f&libraries=services,clusterer,drawing"></script>
				<script>
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				    mapOption = { 
				        center: new daum.maps.LatLng(${y}, ${x}), // 지도의 중심좌표
				        level: 3 // 지도의 확대 레벨
				    };
				
				var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
				
				// 마커가 표시될 위치입니다 
				var markerPosition  = new daum.maps.LatLng(${y}, ${x}); 
				
				// 마커를 생성합니다
				var marker = new daum.maps.Marker({
				    position: markerPosition
				});
				
				// 마커가 지도 위에 표시되도록 설정합니다
				marker.setMap(map);

				// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
				// marker.setMap(null);    
				</script>
				
<br>

<script>
function goBack() {
  window.history.back();
}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>