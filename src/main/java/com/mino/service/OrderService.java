package com.mino.service;

import java.util.List;

import com.mino.domain.OrderCancelDTO;
import com.mino.domain.OrderItemDTO;
import com.mino.domain.OrderSuccessDTO;

public interface OrderService {
	
	//주문 상품 정보
	public List<OrderItemDTO> getGoodsInfo(List<OrderItemDTO> orders);
	
	//주문
	public void order(OrderSuccessDTO osd);
	
	// 주문 취소 
	public void orderCancle(OrderCancelDTO dto);

}
