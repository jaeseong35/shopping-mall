package com.mino.domain;

import java.util.List;

public class OrderItemDTO {

	//뷰에서 전달 받을 값
	private int itemId;
	
	private int itemCount;
	
	//db에서 꺼내올값
	private String itemName;
	
	private int itemPrice;
	
	private double itemDiscount;
	
	//이미지 
	private List<ImageVO> imageList;
	
	// 만들어 낼 값 
    private int salePrice; //할인가격
    
    private int totalPrice; //총 가격
    
    private int point; //포인트
    
    private int totalPoint; //토탈 포인트

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

	public double getItemDiscount() {
		return itemDiscount;
	}

	public void setItemDiscount(double itemDiscount) {
		this.itemDiscount = itemDiscount;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(int totalPoint) {
		this.totalPoint = totalPoint;
	}
    
	public void initSaleTotal() {  //itemPrice , itemCount, itemDiscount를 통해 값을 만듬
		this.salePrice = (int) (this.itemPrice * (1-this.itemDiscount));
		this.totalPrice = this.salePrice*this.itemCount;
		this.point = (int)(Math.floor(this.salePrice*0.05));
		this.totalPoint =this.point * this.itemCount;
	}

	public List<ImageVO> getImageList() {
		return imageList;
	}

	public void setImageList(List<ImageVO> imageList) {
		this.imageList = imageList;
	}

	@Override
	public String toString() {
		return "OrderItemDTO [itemId=" + itemId + ", itemCount=" + itemCount + ", itemName=" + itemName + ", itemPrice="
				+ itemPrice + ", itemDiscount=" + itemDiscount + ", imageList=" + imageList + ", salePrice=" + salePrice
				+ ", totalPrice=" + totalPrice + ", point=" + point + ", totalPoint=" + totalPoint + "]";
	}


	
    
}
