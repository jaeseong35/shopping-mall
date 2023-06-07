package com.mino.service;

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
public class CartServiceTest {

	
	@Autowired
	private CartService cartService;
	
	//등록 테스트
	@Test
	public void addCartTest() throws Exception {
		//given
			String memberId = "admin";
			int itemId = 5000;
			int count = 5;
			
			CartDTO dto = new CartDTO(); 
			dto.setMemberId(memberId);
			dto.setItemId(itemId);
			dto.setItemCount(count);
		
		//when
			int result = cartService.addCart(dto);
		
		//then
			System.out.println("** result : " + result);
		
		
	}
}
