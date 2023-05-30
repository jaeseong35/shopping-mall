package com.mino.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mino.domain.CartDTO;
import com.mino.domain.ImageVO;
import com.mino.domain.ItemVO;
import com.mino.domain.MemberVO;
import com.mino.domain.OrderCancelDTO;
import com.mino.domain.OrderDTO;
import com.mino.domain.OrderItemDTO;
import com.mino.domain.OrderSuccessDTO;
import com.mino.mapper.CartMapper;
import com.mino.mapper.ImageMapper;
import com.mino.mapper.ItemMapper;
import com.mino.mapper.MemberMapper;
import com.mino.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ImageMapper imageMapper;
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private CartMapper cartMapper;

	//주문 정보
	@Override
	public List<OrderItemDTO> getGoodsInfo(List<OrderItemDTO> orders) {
		List<OrderItemDTO> list = new ArrayList<OrderItemDTO>();		
		
		for(OrderItemDTO oid : orders) {
			
			OrderItemDTO orderInfo = orderMapper.orderInfo(oid.getItemId());			

			orderInfo.setItemCount(oid.getItemCount());	
			
			orderInfo.initSaleTotal();
			
			List<ImageVO> imageList = imageMapper.getImageList(orderInfo.getItemId());
			
			orderInfo.setImageList(imageList);
			
			list.add(orderInfo);			
		}
		return list;

}

	//주문 하기
	@Override
	@Transactional
	public void order(OrderSuccessDTO osd) {
		/* 사용할 데이터가져오기 */
		/* 회원 정보 */
		
		System.out.println( "asdfasdfasdf= "+ osd);
		MemberVO member = memberMapper.memberInfo(osd.getMemberId());
		/* 주문 정보 */
		List<OrderDTO> ords = new ArrayList<>();
		for(OrderDTO od : osd.getOrders()) {
			OrderDTO orderItem = orderMapper.getOrderInfo(od.getItemId());
			// 수량 셋팅
			orderItem.setItemCount(od.getItemCount());
			// 기본정보 셋팅
			orderItem.initSaleTotal();
			//List객체 추가
			ords.add(orderItem);
		}
		/* OrderDTO 셋팅 */
		osd.setOrders(ords);
		osd.getOrderPriceInfo();
		
		
		/*DB 주문,주문상품(,배송정보) 넣기*/
		
		/* orderId만들기 및 OrderDTO객체 orderId에 저장 */
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
		String orderId = member.getMemberId() + format.format(date);
		osd.setOrderId(orderId);
		
		/* db넣기 */
		orderMapper.insertOrder(osd);		//shiop_order 등록
		for(OrderDTO oit : osd.getOrders()) {		//shop_orderItem 등록
			oit.setOrderId(orderId);
			orderMapper.insertOrderItem(oit);			
		}
		/* 비용 포인트 변동 적용 */
		
		/* 비용 차감 & 변동 돈(money) Member객체 적용 */
		int calMoney = member.getMoney();
		calMoney -= osd.getOrderFinalSalePrice();
		member.setMoney(calMoney);
		
		/* 포인트 차감, 포인트 증가 & 변동 포인트(point) Member객체 적용 */
		int calPoint = member.getPoint();
		calPoint = calPoint - osd.getuserPoint() + osd.getOrderSavePoint();	// 기존 포인트 - 사용 포인트 + 획득 포인트
		member.setPoint(calPoint);
		
		/* 변동 돈, 포인트 DB 적용 */
		orderMapper.memberMoney(member);
		
		for(OrderDTO oit : osd.getOrders()) {
			/* 변동 재고 값 구하기 */
			ItemVO item = itemMapper.itemInfo(oit.getItemId());
			item.setItemStock(item.getItemStock() - oit.getItemCount());
			/* 변동 값 DB 적용 */
			orderMapper.vaStock(item);
		}
			/* 장바구니 제거 */
			for(OrderDTO oit : osd.getOrders()) {
				CartDTO dto = new CartDTO();
				dto.setMemberId(osd.getMemberId());
				dto.setItemId(oit.getItemId());
				
				cartMapper.deleteOrderCart(dto);
			}
	
	}

	//주문 취소
	@Override
	@Transactional
	public void orderCancle(OrderCancelDTO dto) {
		
		/* 주문, 주문상품 객체 */
		/*회원*/
			MemberVO member = memberMapper.memberInfo(dto.getMemberId());
		/*주문상품*/
			List<OrderDTO> ords = orderMapper.getOrderItemInfo(dto.getOrderId());
			for(OrderDTO ord : ords) {
				ord.initSaleTotal();
			}
		/* 주문 */
			OrderSuccessDTO orw = orderMapper.getOrder(dto.getOrderId());
			orw.setOrders(ords);
			
			orw.getOrderPriceInfo();
			
	/* 주문상품 취소 DB */
			orderMapper.orderCancle(dto.getOrderId());
			
	/* 돈, 포인트, 재고 변환 */
			/* 돈 */
			int calMoney = member.getMoney();
			calMoney += orw.getOrderFinalSalePrice();
			member.setMoney(calMoney);
			
			/* 포인트 */
			int calPoint = member.getPoint();
			calPoint = calPoint + orw.getuserPoint() - orw.getOrderSavePoint();
			member.setPoint(calPoint);
			
				/* DB적용 */
				orderMapper.memberMoney(member);
				
			/* 재고 */
			for(OrderDTO ord : orw.getOrders()) {
				ItemVO item = itemMapper.itemInfo(ord.getItemId());
				item.setItemStock(item.getItemStock() + ord.getItemCount());
				orderMapper.vaStock(item);
			}
	}
}