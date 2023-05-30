package com.mino.service;

import java.util.List;

import com.mino.domain.CateVO;
import com.mino.domain.ImageVO;
import com.mino.domain.ItemVO;
import com.mino.domain.OrderSuccessDTO;
import com.mino.domain.Paging;

public interface AdminService {
	//상품 등록
	public void itemInsert(ItemVO item);
	
	//카테고리 목록
	public List<CateVO> cateList();
	
	//상품 리스트
	public List<ItemVO> shopList(Paging page);
	
	//상품 총 갯수
	public int shopTotal(Paging page);
	
	//상품 정보 보기
	public ItemVO shopView(int itemId);
	
	//상품 수정
	public int shopModify(ItemVO item);
	
	//상품 삭제
	public int shopDelete(int itemId);
	
	//지정된 상품 이미지 정보
	public List<ImageVO> getImageInfo(int itemId);
	
	//주문 상품 리스트
	public List<OrderSuccessDTO> getOrderList(Paging page);

	//주문 총갯수
	public int getOrderTotal(Paging page);
}
