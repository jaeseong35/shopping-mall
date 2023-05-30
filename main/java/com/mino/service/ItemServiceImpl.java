package com.mino.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mino.domain.ImageVO;
import com.mino.domain.ItemVO;
import com.mino.domain.Paging;
import com.mino.mapper.AdminMapper;
import com.mino.mapper.ImageMapper;
import com.mino.mapper.ItemMapper;

@Service
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ImageMapper imageMapper;
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public List<ItemVO> itemList(Paging page) {
		
		//이미지 정보 저장
			List<ItemVO> list = itemMapper.itemList(page);
		
			list.forEach(item -> {
			
			int itemId = item.getItemId();
			
			List<ImageVO> imageList = imageMapper.getImageList(itemId);
			
			item.setImageList(imageList);
			
		});
	
		
		return list;
	}
	
	@Override
	public int itemTotal(Paging page) {
	
		return itemMapper.itemTotal(page);
	}

	@Override
	public ItemVO itemInfo(int itemId) {
		
		ItemVO itemInfo = itemMapper.itemInfo(itemId);
		itemInfo.setImageList(adminMapper.getImageInfo(itemId));
		
		return itemInfo;
	}
	

}
