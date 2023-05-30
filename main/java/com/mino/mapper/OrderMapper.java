package com.mino.mapper;

import java.util.List;

import com.mino.domain.ItemVO;
import com.mino.domain.MemberVO;
import com.mino.domain.OrderDTO;
import com.mino.domain.OrderItemDTO;
import com.mino.domain.OrderSuccessDTO;

public interface OrderMapper {

	//주문 상품 정보	
	public OrderItemDTO orderInfo(int itemId);
	
	//주문 정보(주문처리)
	public OrderDTO getOrderInfo(int itemId);
	
	//주문 테이블 등록
	public int insertOrder(OrderSuccessDTO osd);
	
	//주문 물품 테이블 등록
	public int insertOrderItem(OrderDTO od);
	
	//주문 금액 변동
	public int memberMoney(MemberVO member);
	
	//상품 재고
	public int vaStock(ItemVO item);
	
	//주문취소
	public int orderCancle(String orderId);
	
	//주문 상품 정보 (취소)
	public List<OrderDTO> getOrderItemInfo(String orderId);
	
	// 주문 정보(주문취소)
	public OrderSuccessDTO getOrder(String orderId);
}
