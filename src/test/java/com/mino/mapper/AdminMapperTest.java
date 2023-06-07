package com.mino.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mino.domain.ImageVO;
import com.mino.domain.ItemVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class AdminMapperTest {

	@Autowired
	private AdminMapper adminMapper;
	
//	@Test
//	public void itemEnrollTest() throws Exception{
//		ItemVO item = new ItemVO();
//		
//		item.setItemName("mapper테스트");
//		item.setCateCode("1111");
//		item.setItemContents("물품 내용");
//		item.setItemIntro("물품 소개");
//		item.setItemDiscount(0.22);
//		item.setItemPrice(10000);
//		item.setItemPrice(10000);
//		
//		adminMapper.itemEnroll(item);
//	}
	
//	@Test
//	public void itemListTest() throws Exception{
//		System.out.println("cateList=" +adminMapper.cateList());
//	}
	
	/* 상품 리스트 & 상품 총 갯수 */
//	@Test
//	public void shopListTest() {
//		
//		Paging page = new Paging();
//		
//		/* 검색조건 */
//		page.setKeyword("물품");
//		
//		/* 검색 리스트 */
//		List list = adminMapper.shopList(page);
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println("result......." + i + " : " + list.get(i));
//		}
//		
//		/* 상품 총 갯수 */
//		int result = adminMapper.shopTotal(page);
//		System.out.println("resutl.........." + result);
//		
//		
//	}
	
//	@Test
//	public void shopViewTest() {
//		int itemId = 300;
//		
//		log.info("검색결과="+adminMapper.shopView(itemId));
//	}
	
//	@Test
//	public void shopModifyTest() {
//		ItemVO item =new ItemVO();
//		
//		item.setItemId(9);
//		item.setItemName("222");
//		item.setCateCode("101002");
//		item.setItemPrice(3000);
//		item.setItemStock(50);
//		item.setItemDiscount(0.5);
//		item.setItemIntro("fffff");
//		item.setItemContents("dddddd");
//		
//		adminMapper.shopModify(item);
//		
//	}
	
//	@Test
//	public void shopDeleteTest() {
//		int item = 10;
//		
//		adminMapper.shopDelete(item);
//		
//	}
	
//	@Test
//	public void imgInsertTest() {
//
//			
//			ImageVO img = new ImageVO();
//			
//			img.setItemId(11);
//			img.setFileName("test");
//			img.setUploadPath("test");
//			img.setUuid("test2");
//			
//			adminMapper.imgInsert(img);
//			
//		
//	}
	
//	@Test
//	public void deleteImageAllTest() {
//		
//		int item = 519;
//		adminMapper.deleteImageAll(item);
//	}
	
//	@Test
//	public void checkImageListTest() {
//		
//		adminMapper.checkFileList();
//	}
	
	
	/* 지정 상품 이미지 정보 얻기 */
	@Test
	public void getAttachInfoTest() {
		
		int itemId = 520;
		
		List<ImageVO> list = adminMapper.getImageInfo(itemId);
		
		System.out.println("list : " + list);
		
	}
	
	
}
