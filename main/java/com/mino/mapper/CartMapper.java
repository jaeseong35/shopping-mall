package com.mino.mapper;

import java.util.List;

import com.mino.domain.CartDTO;

public interface CartMapper {
	
	/* 카트 추가 */
	public int addCart(CartDTO cart) throws Exception;
	
	/* 카트 삭제 */
	public int deleteCart(int cartId);
	
	/* 카트 수량 수정 */
	public int modifyCount(CartDTO cart);
	
	/* 카트 목록 */
	public List<CartDTO> cartList(String memberId);	
	
	/* 카트 확인 */
	public CartDTO checkCart(CartDTO cart);
	
	//장바구니 제거
		public int deleteOrderCart(CartDTO cart);

}
