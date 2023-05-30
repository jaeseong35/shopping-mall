<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<link rel="stylesheet" href="/resources/css/itemView.css?after">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>
<div class="wrapper">
	<div class="wrap">
		<div class="top_gnb_area">
			<ul class="list">
				<!-- 로그인 전 -->
				<c:if test = "${member == null}">
                <li >
                    <a href="/member/login">로그인</a>
                </li>
                <li>
                    <a href="/member/sign">회원가입</a>
                </li>
                 </c:if>
                 	<!-- 로그인 후 -->
				<c:if test = "${member != null}">
					<!-- 관리자 -->
					<c:if test="${member.adminCk == 1 }">
                        <li><a href="/admin/main">관리자 페이지</a></li>
                    </c:if>
                <li >
                    <a href="" id="gnb_logout_button">로그아웃</a>
                </li>
           
                 <li>
                    <a href="/cart/${member.memberId}">장바구니</a>
                </li>
                 </c:if>
                   
            </ul>    
		</div>
		<div class="top_area">
			<div class="logo_area">
				<a href="/"><img src="/resources/img/logo.png"></a>
			</div>
			<div class="search_area">
				<div class="search_wrap">
                		<form id="searchForm" action="/search" method="get">
                			<div class="search_input">
                				<input type="text" name="keyword" <c:out value="${pageMaker.page.keyword}"/>">
                    			<button class='btn search_btn'>검 색</button>                				
                			</div>
                		</form>
                	</div>
			</div>
			<div class="login_area">
			
			
				<!-- 로그인한 상태 -->
				<c:if test="${member != null}">
				<div class="login_success_area">
                        <span>회원 : ${member.memberName}</span>
                        <span>충전금액 : <fmt:formatNumber value="${member.money}" pattern="\#,###.##"/></span>
                        <span>포인트 : <fmt:formatNumber value="${member.point}" pattern="\#,###.##"/></span>
                         <a href="/member/logout">로그아웃</a>
                 </div>
				</c:if>
			</div>
			<div class="clearfix"></div>			
		</div>
		<div class="navi_bar_area">
			<div class="navi_bar_area">
			<div class="dropdown">
			    <button class="dropbtn">상의 
			      <i class="fa fa-caret-down"></i>
			    </button>
			    <div class="dropdown-content">
			    	<a href="/search?type=C&cateCode=101001">반팔</a>
			    	<a href="/search?type=C&cateCode=101002">니트</a>
			    	<a href="/search?type=C&cateCode=101003">후드</a>    		      		      
			    </div>			
			</div>
			
				<div class="dropdown">
			    <button class="dropbtn">아우터 
			      <i class="fa fa-caret-down"></i>
			    </button>
			    <div class="dropdown-content">
					<a href="/search?type=C&cateCode=103001">자켓</a> 
			    	<a href="/search?type=C&cateCode=103002">가디건</a> 
			    	<a href="/search?type=C&cateCode=103003">코트</a>    		      		      
			    </div>			
			</div>
			
			<div class="dropdown">
			    <button class="dropbtn">하의 
			      <i class="fa fa-caret-down"></i>
			    </button>
			    <div class="dropdown-content">
			    	<a href="/search?type=C&cateCode=102001">반바지</a> 
			    	<a href="/search?type=C&cateCode=102002">슬렉스</a> 
			    	<a href="/search?type=C&cateCode=102003">청바지</a>     		      		      
			    </div>			
			</div>
			
				<div class="dropdown">
			    <button class="dropbtn">신발 
			      <i class="fa fa-caret-down"></i>
			    </button>
			    <div class="dropdown-content">
			    	<a href="/search?type=C&cateCode=104001">구두</a> 
			    	<a href="/search?type=C&cateCode=104002">스니커즈</a> 
			    	<a href="/search?type=C&cateCode=104003">슬리퍼</a>       		      		      
			    </div>			
			</div>
		
		</div>		
		</div>
		<div class="content_area">
				<div class="line">
			</div>			
			<div class="content_top">
				<div class="ct_left_area">
					<div class="image_wrap" data-itemid="${itemInfo.imageList[0].itemId}" data-path="${itemInfo.imageList[0].uploadPath}" data-uuid="${itemInfo.imageList[0].uuid}" data-filename="${itemInfo.imageList[0].fileName}">
						<img>
					</div>				
				</div>
				<div class="ct_right_area">
					<div class="title">
						<h1>
							${itemInfo.itemName}
						</h1>
					</div>
					<div class="line">
					</div>
					<div class="line">
					</div>	
					<div class="price">
						<div class="sale_price">정가 : <fmt:formatNumber value="${itemInfo.itemPrice}" pattern="#,### 원" /></div>
						<div class="discount_price">
							판매가 : <span class="discount_price_number"><fmt:formatNumber value="${itemInfo.itemPrice - (itemInfo.itemPrice*itemInfo.itemDiscount)}" pattern="#,### 원" /></span> 
							[<fmt:formatNumber value="${itemInfo.itemDiscount*100}" pattern="###" />% 
							<fmt:formatNumber value="${itemInfo.itemPrice*itemInfo.itemDiscount}" pattern="#,### 원" /> 할인]</div>							
					
						<div>
							적립 포인트 : <span class="point_span"></span>원
						</div>
					
					</div>			
					<div class="line">
					</div>	
					<div class="button">						
						<div class="button_quantity">
							주문수량
							<input type="text" class="quantity_input" value="1">
							<span>
								<button class="plus_btn">+</button>
								<button class="minus_btn">-</button>
							</span>
						</div>
						<div class="button_set">
							<a class="btn_cart">장바구니 담기</a>
							<a class="btn_buy">바로구매</a>
						</div>
					</div>
				</div>
			</div>
			<div class="line">
			</div>				
			<div class="content_middle">
				<div class="item_intro">
					${itemInfo.itemIntro}
				</div>
				<div class="item_content">
					${itemInfo.itemContents }
				</div>
			</div>
		</div>
		
		<!-- 주문 form -->
			<form action="/order/${member.memberId}" method="get" class="order_form">
				<input type="hidden" name="orders[0].itemId" value="${itemInfo.itemId}">
				<input type="hidden" name="orders[0].itemCount" value="">
			</form>
			
		  <!-- Footer 영역 -->
        <div class="footer_nav">
            <div class="footer_nav_container">
               COPYRIGHT(C) SHOP ALL RIGHTS RESERVED.
            </div>
        </div> <!-- class="footer_nav" -->
        
	</div>
</div>

<script>

$(document).ready(function(){
	
	/* 이미지 삽입 */
	const bobj = $(".image_wrap");
	
	if(bobj.data("itemid")){
		const uploadPath = bobj.data("path");
		const uuid = bobj.data("uuid");
		const fileName = bobj.data("filename");
		
		const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
		
		bobj.find("img").attr('src', '/displayImg?fileName=' + fileCallPath);
	} else {
		bobj.find("img").attr('src', '/resources/img/noimg.JPG');
	}
	//포인트
	let salePrice = "${itemInfo.itemPrice - (itemInfo.itemPrice * itemInfo.itemDiscount)}"
	let point = salePrice * 0.05;
	point = Math.floor(point); //소수점 나머지 버리도록
	$(".point_span").text(point);
	
});	



let quantity = $(".quantity_input").val();
$(".plus_btn").on("click",function (){
	$(".quantity_input").val(++quantity);
});
$(".minus_btn").on("click",function (){
	if(quantity >1){
	$(".quantity_input").val(--quantity);
	}
});

//서버로 전송할 데이터
const form = {
		memberId : '${member.memberId}',
		itemId : '${itemInfo.itemId}',
		itemCount : ''
}

// 장바구니 추가 버튼
$(".btn_cart").on("click",function(e){
	form.itemCount = $(".quantity_input").val();
	$.ajax({
		url:'/cart/add',
		type:'post',
		data:form,
		success: function (result) {
			if(result == '0'){
				alert("장바구니에 추가를 하지 못하였습니다.");
			} else if(result == '1'){
				alert("장바구니에 추가되었습니다.");
			} else if(result == '2'){
				alert("장바구니에 이미 추가되어져 있습니다.");
			} else if(result == '5'){
				alert("로그인이 필요합니다.");	
			}
		}
	})
	
});

// 바로구매 버튼
$(".btn_buy").on("click", function(){
	let itemCount = $(".quantity_input").val();
	$(".order_form").find("input[name='orders[0].itemCount']").val(itemCount);
	console.log(itemCount);

	$(".order_form").submit();
});



</script>


</body>
</html>