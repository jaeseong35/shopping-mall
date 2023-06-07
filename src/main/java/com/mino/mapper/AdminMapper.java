package com.mino.mapper;

import java.util.List;

import com.mino.domain.CateVO;
import com.mino.domain.ImageVO;
import com.mino.domain.ItemVO;
import com.mino.domain.OrderSuccessDTO;
import com.mino.domain.Paging;

public interface AdminMapper {

	//상품 등록
	public void itemInsert(ItemVO item);
	
	//카테고리 리스트
	public List<CateVO> cateList();
	
	//상품 리스트
	public List<ItemVO> shopList(Paging page);
	
	//상품 총 갯수
	public int shopTotal(Paging page);
	
	//상품 조회
	public ItemVO shopView(int itemId);
	
	//상품 수정
	public int shopModify(ItemVO item);
	
	//상품 삭제
	public int shopDelete(int itemId);
	
	//이미지 등록
	public void imgInsert(ImageVO img);
	
	//itemId값에 맞는 이미지 전체 삭제
	public void deleteImageAll(int itemId);
	
	//어제자 날짜 이미지 리스트
	public List<ImageVO> checkFileList();
	
	//지정 상품 이미지정보
	public List<ImageVO> getImageInfo(int itemId);
	
	//상품 리스트
	public List<OrderSuccessDTO> getOrderList(Paging page);
	
	//주문 총 갯수
	public int getOrderTotal(Paging page);
}

