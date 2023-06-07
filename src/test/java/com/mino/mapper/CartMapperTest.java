package com.mino.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mino.domain.CartDTO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CartMapperTest {
	
	@Autowired
	private CartMapper cartMapper;
	
	//카트추가 테스트
//	@Test
//	public void addCartTest() {
//		
//		String memberId = "admin";
//		int itemId = 300;
//		int count = 2;
//		
//		CartDTO cart = new CartDTO();
//		
//		cart.setMemberId(memberId);
//		cart.setItemId(itemId);
//		cart.setItemCount(count);
//		
//		int result = 0;
//		result = cartMapper.addCart(cart);
//		
//		System.out.println("결과 : " + result);
//		
//	}	
	
	//카트 삭제

//	@Test
//	public void deleteCartTest() {
//		int cartId = 1;
//		
//		cartMapper.deleteCart(cartId);
//
//	
//	
//	}
	
	// 카트 수량 수정 

//	@Test
//	public void modifyCartTest() {
//		int cartId = 2;
//		int count = 5;
//		
//		CartDTO cart = new CartDTO();
//		cart.setCartId(cartId);
//		cart.setItemCount(count);
//		
//		cartMapper.modifyCount(cart);
//		
//	}

	/* 카트 목록 */ 

//	@Test
//	public void cartLIstTest() {
//		String memberId = "admin";
//		
//		
//		List<CartDTO> list = cartMapper.cartList(memberId);
//		for(CartDTO cart : list) {
//			System.out.println(cart);
//			cart.saleTotal();
//			System.out.println("init cart : " + cart);
//		}
//		
//		
//	
//	}
	
	/* 카트 확인 */

	@Test
	public void checkCartTest() {
		
		String memberId = "admin";
		int itemId = 300;
		
		CartDTO cart = new CartDTO();
		
		cart.setMemberId(memberId);
		cart.setItemId(itemId);
		
		CartDTO resutlCart = cartMapper.checkCart(cart);
		System.out.println("결과 : " + resutlCart);
		
	}
	
	
}
