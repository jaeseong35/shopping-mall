package com.mino.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mino.domain.ItemVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ItemServiceTest {

	@Autowired
	private ItemService itemService;
	/*상품 상세 정보*/
	@Test
	public void getGoodsInfoTest() {
		
		int itemId = 300;
		
		ItemVO itemInfo = itemService.itemInfo(itemId);
		
		System.out.println("==결과==");
		System.out.println("전체 : " + itemInfo);
		System.out.println("itemId : " + itemInfo.getItemId() );
		System.out.println("이미지 정보 : " + itemInfo.getImageList().isEmpty());
		
		
	}
}
