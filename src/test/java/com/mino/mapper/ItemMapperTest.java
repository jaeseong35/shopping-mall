package com.mino.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mino.domain.ItemVO;
import com.mino.domain.Paging;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ItemMapperTest {
	
	@Autowired
	private ItemMapper itemMapper;
	
//	@Test
//	public void itemListTest() {
//		
//		Paging page = new Paging();
//		
////		
//		page.setKeyword("의상");
//		System.out.println("page" + page);
//		
//		List<ItemVO> list = itemMapper.itemList(page);
//		System.out.println("list : " + list);
//		
//		System.out.println("==========");
//		
//		int itemTotal = itemMapper.itemTotal(page);
//		System.out.println("totla : " + itemTotal);
//	
//	
//	}
	
//	@Test
//	public void itemInfoTest() {
//		int itemId = 26;
//		ItemVO itemInfo = itemMapper.itemInfo(itemId);
//		System.out.println("===========================");
//		System.out.println(itemInfo);
//		System.out.println("===========================");
//		
//	}
	
/* 검색 (동적 쿼리 적용) - 책제목*/
//	
//	@Test 
//	public void getGoodsListTest2() {
//		Paging page = new Paging();
//		String type = "T";
//		String keyword = "";			// 테이블에 등록된 책 제목 데이터
//		//String keyword = "없음";				// 테이블에 등록되지 않은 데이터
//		String catecode = "102001";	
//		
//		page.setType(type);
//		page.setKeyword(keyword);
//		page.setCateCode(catecode);
//		
//		List<ItemVO> list = itemMapper.itemList(page);
//		
//		System.out.println("page : " + page);
//		System.out.println("list : " + list);
//		
//	}
//	//카테고리
//	@Test 
//	public void getGoodsListTest3() {
//		Paging page = new Paging();
//		String type = "C";
//		String keyword = "";
//		String catecode = "102002";		
//		
//		page.setType(type);
//		page.setKeyword(keyword);
//		page.setCateCode(catecode);
//		
//		List<ItemVO> list = itemMapper.itemList(page);
//		
//		System.out.println("page : " + page);
//		System.out.println("list : " + list);
//	}
	
/* 검색 (동적 쿼리 적용) - 카테고리 + 책 제목 */
	
	@Test 
	public void getGoodsListTest5() {
		Paging page = new Paging();
		String type = "CT";			// 카테고리에 존재하는 책
		String keyword = "의";	// 카테고리에 존재하지 않는 책
		//String keyword = "없음";
		String catecode = "101002";
		
		page.setType(type);
		page.setKeyword(keyword);
		page.setCateCode(catecode);
		
		List<ItemVO> list = itemMapper.itemList(page);
		
		System.out.println("page : " + page);
		System.out.println("list : " + list);	
		
	}

}
