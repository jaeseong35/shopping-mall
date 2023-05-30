package com.mino.service;

import java.util.List;

import com.mino.domain.ItemVO;
import com.mino.domain.Paging;

public interface ItemService {
	
	//물품 검색
	public List<ItemVO> itemList(Paging page);
	
	//물품 총 갯수
	public int itemTotal(Paging page);
	
	//상품 정보
	public ItemVO itemInfo(int itemId);

}
