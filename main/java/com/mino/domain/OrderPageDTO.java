package com.mino.domain;

import java.util.List;

public class OrderPageDTO {
	
	//orderItemDTO 클래스의 객체를 요소로 가지는 List타입의 변수
	private List<OrderItemDTO> orders;
	

	public List<OrderItemDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderItemDTO> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "OrderPageDTO [orders=" + orders + "]";
	}

	
	
}
