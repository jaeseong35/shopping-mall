package com.mino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mino.domain.CartDTO;
import com.mino.domain.ImageVO;
import com.mino.domain.ItemVO;
import com.mino.mapper.CartMapper;
import com.mino.mapper.ImageMapper;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private ImageMapper imageMapper;
	
	@Override
	public int addCart(CartDTO cart) {
		
		CartDTO checkCart = cartMapper.checkCart(cart);
		
		if(checkCart != null) {
			return 2;
		}
		//장바구니 등록 에러시 0을 반환 
		try {
			return cartMapper.addCart(cart);
			
		} catch (Exception e) {
			
			return 0;
		}
	}

	@Override
	public List<CartDTO> cartList(String memberId) {
		List<CartDTO> cart = cartMapper.cartList(memberId);
		
		
		for(CartDTO dto : cart) {
			dto.saleTotal();//salePrice, totalPrice, point, totalPoint 값이 없어서 초기화
			
			//이미지 정보 넣기
			int itemId = dto.getItemId();
			List<ImageVO> imageList = imageMapper.getImageList(itemId);
			
			dto.setImageList(imageList);
			
		}
		return cart;
		
	}

	@Override
	public int modifyCount(CartDTO cart) {
		
		return cartMapper.modifyCount(cart);
	}

	@Override
	public int deleteCart(int cartId) {
		
		return cartMapper.deleteCart(cartId);
	}

}
