package com.mino.service;

import java.util.List;

import com.mino.domain.CartDTO;

public interface CartService {
	// 장바구니 추가
	public int addCart(CartDTO cart);
	
	//장바구니 정보
	public List<CartDTO> cartList(String memberId);
	
	//장바구니 수량 수정
	public int modifyCount(CartDTO cart);
	
	//장바구니 삭제
	public int deleteCart(int cartId);
}
