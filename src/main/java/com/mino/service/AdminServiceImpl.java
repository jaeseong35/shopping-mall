package com.mino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mino.domain.CateVO;
import com.mino.domain.ImageVO;
import com.mino.domain.ItemVO;
import com.mino.domain.OrderSuccessDTO;
import com.mino.domain.Paging;
import com.mino.mapper.AdminMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminMapper adminMapper;

	@Transactional
	@Override
	public void itemInsert(ItemVO item) {
		
		log.info("service=itemEnroll");
		adminMapper.itemInsert(item);
	
		
		if(item.getImageList() == null || item.getImageList().size() <= 0) {
			return;
		}
		//이미지 db에 저장 , item에 저장되어있는 itemId꺼내오기
		for(ImageVO img : item.getImageList()) {
			img.setItemId(item.getItemId());
			
			adminMapper.imgInsert(img);
		}
	}

	@Override
	public List<CateVO> cateList() {
		
		return adminMapper.cateList();
	}

	@Override
	public List<ItemVO> shopList(Paging page) {
		
		log.info("shopList=(service)");
		return adminMapper.shopList(page);
	}

	@Override
	public int shopTotal(Paging page) {
		
		log.info("shopList==(service)");
		return adminMapper.shopTotal(page);
	}

	@Override
	public ItemVO shopView(int itemId) {
		
		return adminMapper.shopView(itemId);
	}

	@Transactional //두개이상의 쿼리 사용 하였기 때문에 트랜젝션 사용
	@Override
	public int shopModify(ItemVO item) {
		
		int result = adminMapper.shopModify(item);
		
		if(result == 1 && item.getImageList() !=null && item.getImageList().size() > 0) {
			adminMapper.deleteImageAll(item.getItemId());
			
			for(ImageVO img : item.getImageList()) {
				img.setItemId(item.getItemId());
				adminMapper.imgInsert(img);
			}
		}else if(item.getImageList() ==null ){   //수정시 이미지 삭제 하면 
			adminMapper.deleteImageAll(item.getItemId());
		}
		System.out.println("item===="+item.getImageList());
		System.out.println("item===="+item.getItemId());
		
		return result;
	}
	@Transactional
	@Override
	public int shopDelete(int itemId) {
		
		adminMapper.deleteImageAll(itemId);
		
		return adminMapper.shopDelete(itemId);
	}

	@Override
	public List<ImageVO> getImageInfo(int itemId) {
		
		return adminMapper.getImageInfo(itemId);
	}

	@Override
	public List<OrderSuccessDTO> getOrderList(Paging page) {
		return adminMapper.getOrderList(page);
	}

	@Override
	public int getOrderTotal(Paging page) {
		
		return adminMapper.getOrderTotal(page);
	}



}
