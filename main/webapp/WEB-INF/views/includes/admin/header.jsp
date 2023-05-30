<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
   <div class="wrapper">
        <div class="wrap">
            <!-- gnv_area -->    
            <div class="top_gnb_area">
                <ul class="list">    
                    <li><a href="/">메인 페이지</a></li>
                    <li><a href="/member/logout">로그아웃</a></li>
                </ul>
            </div>
            <!-- top_subject_area -->
            <div class="admin_top_wrap">
                <span>관리자 페이지</span>
                
            </div>
            <!-- contents-area -->
            <div class="admin_wrap">
                <!-- 네비영역 -->
                <div class="admin_navi_wrap">
                    
              <ul>
                  <li >
                      <a class="admin_list_01" href="/admin/shopManage">상품 등록</a>
                  </li>
                  <li>
                      <a class="admin_list_02" href="/admin/shopList">상품 관리</a>
                  </li>       
                  <li>
	                      <a class="admin_list_06" href="/admin/orderList">주문 현황</a>                            
	               </li>                                                                                           
              </ul>
<!-- 
                    <div class="admin_list_01">
                        <a>상품 관리</a>
                    </div>
                     -->
                </div>
</body>
</html>