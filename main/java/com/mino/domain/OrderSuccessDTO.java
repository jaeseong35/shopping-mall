package com.mino.domain;

import java.util.Date;
import java.util.List;


//배송지 정보가 들어가있는 DTO
public class OrderSuccessDTO {

	// 주문 번호
	private String orderId;
	
	// 배송 받는이
	private String addressName;
	
	// 주문 회원 아이디 
	private String memberId;
	
	// 우편번호 
	private String memberAddr1;
	
	// 회원 주소 
	private String memberAddr2;
	
	// 회원 상세주소 
	private String memberAddr3;
	
	// 주문 상태 
	private String orderState;
	
	// 주문 상품 
	private List<OrderDTO> orders;	
	
	// 배송비 
	private int deliveryPay;
	
	// 사용 포인트 
	private int userPoint;
	
	// 주문 날짜 
	private Date orderDate;
	
	// DB테이블 존재 하지 않는 데이터 

	// 판매가(모든 상품 비용) 
	private int orderSalePrice;
	
	// 적립 포인트
	private int orderSavePoint;
	
	// 최종 판매 비용
	private int orderFinalSalePrice;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getaddressName() {
		return addressName;
	}

	public void setaddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberAddr1() {
		return memberAddr1;
	}

	public void setMemberAddr1(String memberAddr1) {
		this.memberAddr1 = memberAddr1;
	}

	public String getMemberAddr2() {
		return memberAddr2;
	}

	public void setMemberAddr2(String memberAddr2) {
		this.memberAddr2 = memberAddr2;
	}

	public String getMemberAddr3() {
		return memberAddr3;
	}

	public void setMemberAddr3(String memberAddr3) {
		this.memberAddr3 = memberAddr3;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public List<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
	}

	public int getdeliveryPay() {
		return deliveryPay;
	}

	public void setdeliveryPay(int deliveryPay) {
		this.deliveryPay = deliveryPay;
	}

	public int getuserPoint() {
		return userPoint;
	}

	public void setuserPoint(int userPoint) {
		this.userPoint = userPoint;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderSalePrice() {
		return orderSalePrice;
	}

	public void setOrderSalePrice(int orderSalePrice) {
		this.orderSalePrice = orderSalePrice;
	}

	public int getOrderSavePoint() {
		return orderSavePoint;
	}

	public void setOrderSavePoint(int orderSavePoint) {
		this.orderSavePoint = orderSavePoint;
	}

	public int getOrderFinalSalePrice() {
		return orderFinalSalePrice;
	}

	public void setOrderFinalSalePrice(int orderFinalSalePrice) {
		this.orderFinalSalePrice = orderFinalSalePrice;
	}

	
	public void getOrderPriceInfo() {
		// 상품 비용 & 적립포인트
			for(OrderDTO order : orders) {
				orderSalePrice += order.getTotalPrice();
				orderSavePoint += order.getTotalSavePoint();
			}
		// 배송비용
			if(orderSalePrice >= 30000) {
				deliveryPay = 0;
			} else {
				deliveryPay = 3000;
			}
		// 최종 비용(상품 비용 + 배송비 - 사용 포인트)
			orderFinalSalePrice = orderSalePrice + deliveryPay - userPoint;
	}
	@Override
	public String toString() {
		return "OrderSuccessDTO [orderId=" + orderId + ", addressName=" + addressName + ", memberId=" + memberId
				+ ", memberAddr1=" + memberAddr1 + ", memberAddr2=" + memberAddr2 + ", memberAddr3=" + memberAddr3
				+ ", orderState=" + orderState + ", orders=" + orders + ", deliveryPay=" + deliveryPay + ", userPoint="
				+ userPoint + ", orderDate=" + orderDate + ", orderSalePrice=" + orderSalePrice + ", orderSavePoint="
				+ orderSavePoint + ", orderFinalSalePrice=" + orderFinalSalePrice + "]";
	}
	
	
}
