package com.mino.service;

import java.util.ArrayList;
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
public class AdminServiceTest {
	
	@Autowired
	private AdminService adminService;
	
	@Test
	public void itemInsertTest() throws Exception{
		ItemVO item = new ItemVO();
		
		item.setItemName("mapper테스트");
		item.setCateCode("101001");
		item.setItemPrice(10100);
		item.setItemStock(50);
		item.setItemDiscount(0.22);
		item.setItemIntro("물품 소개");
		item.setItemContents("물품 내용");

		
		// 이미지 정보
		List<ImageVO> imageList = new ArrayList<ImageVO>(); 
				
		ImageVO image1 = new ImageVO();
		ImageVO image2 = new ImageVO();
				
		image1.setFileName("test Image 1");
		image1.setUploadPath("test image 1");
		image1.setUuid("test1111");
				
		image2.setFileName("test Image 2");
		image2.setUploadPath("test image 2");
		image2.setUuid("test2222");
				
		imageList.add(image1);
		imageList.add(image2);
				
		item.setImageList(imageList);        
				
		adminService.itemInsert(item);
				
		System.out.println(" 결과 : " + item);
					
	}
	
//	@Test
//	public void shopModifyServiceTest() {
//		ItemVO item =new ItemVO();
//		
//		item.setItemId(9);
//		item.setItemName("333");
//		item.setCateCode("101003");
//		item.setItemPrice(5000);
//		item.setItemStock(30);
//		item.setItemDiscount(0.5);
//		item.setItemIntro("zzz");
//		item.setItemContents("zzz");
//		
//		adminService.shopModify(item);
//		
//	}

}
