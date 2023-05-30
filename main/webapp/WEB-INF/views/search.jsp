<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<link rel="stylesheet" href="resources/css/search.css?after">
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
				<a href="/"><img src="resources/img/logo.png"></a>
			</div>
			<div class="search_area">
				<div class="search_wrap">
                		<form id="searchForm" action="/search" method="get">
                			<div class="search_input">
                			<select name="type">
                					<option value="T">물품명</option>
                				
                				</select>
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
			<!-- 게시물 o -->
			<c:if test="${listcheck != 'empty'}">
			<div class="list_search_result">
					<table class="type_list">
						<colgroup>
							<col width="110">
							<col width="*">
							<col width="120">
							<col width="120">
							<col width="120">
						</colgroup>
						<tbody id="searchList>">
							<c:forEach items="${list}" var="list">
								<tr>
									<td class="image">
									<div class="image_wrap" data-itemid="${list.imageList[0].itemId}" data-path="${list.imageList[0].uploadPath}" data-uuid="${list.imageList[0].uuid}" data-filename="${list.imageList[0].fileName}">
										<img>
									</div>
									</td>
									<td class="detail">
										<div class="category">
											[${list.cateName}]
										</div>
										<div class="title">
											<a href="/itemView/${list.itemId}">
											${list.itemName}</a> 
										</div>
						
									</td>
									<td class="info">
										<div class="rating">
											평점(추후 추가)
										</div>
									</td>
									<td class="price">
										<div class="org_price">
											<del>
												<fmt:formatNumber value="${list.itemPrice}" pattern="#,### 원" />
											</del>
										</div>
										<div class="sell_price">
											<strong>
												<fmt:formatNumber value="${list.itemPrice * (1-list.itemDiscount)}" pattern="#,### 원" />
											</strong>
										</div>
									</td>
									<td class="option"></td>
								</tr>
							</c:forEach>
						</tbody>
					
					</table>
				</div>
				
				<!-- 페이지 이동 인터페이스 -->
				<div class="pageMaker_wrap">
					<ul class="pageMaker">
	                			
						<!-- 이전 버튼 -->
						<c:if test="${pageMaker.prev }">
	               			<li class="pageMaker_btn prev">
	               				<a href="${pageMaker.pageStart -1}">이전</a>
	               			</li>
						</c:if>
	               			
	               		<!-- 페이지 번호 -->
	               		<c:forEach begin="${pageMaker.pageStart }" end="${pageMaker.pageEnd }" var="num">
	               			<li class="pageMaker_btn ${pageMaker.page.pageNum == num ? 'active':''}">
	               				<a href="${num}">${num}</a>
	               			</li>	
	               		</c:forEach>
	               		
	                   	<!-- 다음 버튼 -->
	                   	<c:if test="${pageMaker.next}">
	                   		<li class="pageMaker_btn next">
	                   			<a href="${pageMaker.pageEnd + 1 }">다음</a>
	                   		</li>
	                   	</c:if>
					</ul>
				</div>
				
				<form id="moveForm" action="/search" method="get" >
					<input type="hidden" name="pageNum" value="${pageMaker.page.pageNum}">
					<input type="hidden" name="amount" value="${pageMaker.page.amount}">
					<input type="hidden" name="keyword" value="${pageMaker.page.keyword}">
					<input type="hidden" name="type" value="${pageMaker.page.type}">
				</form>
			</c:if>
			<!-- 게시물 x -->
			<c:if test="${listcheck == 'empty'}">
				<div class="table_empty">
					검색결과가 없습니다.
				</div>
			</c:if>
		</div>
		

        
        	 <!-- Footer 영역 -->
        <div class="footer_nav">
            <div class="footer_nav_container">
               COPYRIGHT(C) SHOP ALL RIGHTS RESERVED.
            </div>
        </div> <!-- class="footer_nav" -->
	</div>
</div>

<script>

/* 페이지 이동 버튼 */
const moveForm = $('#moveForm');

$(".pageMaker_btn a").on("click", function(e){
	
	e.preventDefault();
	
	moveForm.find("input[name='pageNum']").val($(this).attr("href"));
	
	moveForm.submit();
	
});

$(document).ready(function(){
	// 검색 타입 selected
	const selectedType = '<c:out value="${pageMaker.page.type}"/>';
	if(selectedType != ""){
		$("select[name='type']").val(selectedType).attr("selected", "selected");	
	}
	
	/* 이미지 삽입 */
	$(".image_wrap").each(function(i, obj){
		
		const bobj = $(obj);
		
		if(bobj.data("itemid")){
		const uploadPath = bobj.data("path");
		const uuid = bobj.data("uuid");
		const fileName = bobj.data("filename");
		
		const fileCallPath = encodeURIComponent(uploadPath + "/s_" + uuid + "_" + fileName);
		
		$(this).find("img").attr('src', '/displayImg?fileName=' + fileCallPath);
		}else {
			$(this).find("img").attr('src', '/resources/img/noimg.JPG');
		}
	});
	
	
	
});

$("#gnb_logout_button").click(function () {
	
	$.ajax({
		type:"Post",
		url:"/member/logout",
		success:function(data){
			alert("로그아웃");
			document.location.reload(); //로그아웃하면 새로고침 되도록, 세션 변경사항이 화면에 반영
		}
		
	});
});
</script>

</body>
</html>