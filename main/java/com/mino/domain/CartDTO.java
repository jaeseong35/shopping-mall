package com.mino.domain;

import java.util.List;

public class CartDTO {
	
	//cart 테이블 목록
	private int cartId;
    
    private String memberId;
    
    private int itemId;
    
    private int itemCount;
    
    //item
    
    private String itemName;
    
    private int itemPrice;
    
    private double itemDiscount;
    
    // 금액 추가
    private int salePrice;
    
    private int totalPrice;
    
    private int point;
    
    private int totalPoint;
    
    // 상품 이미지 
	private List<ImageVO> imageList;
    

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

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

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

	public int getTotalPrice() {
		return totalPrice;
	}
	
	// salePrice, totalPrice 초기화 
	public void saleTotal() {
		this.salePrice = (int) (this.itemPrice * ( 1 - this.itemDiscount ));
		this.totalPrice = this.salePrice * this.itemCount;
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
		return "CartDTO [cartId=" + cartId + ", memberId=" + memberId + ", itemId=" + itemId + ", itemCount="
				+ itemCount + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemDiscount=" + itemDiscount
				+ ", salePrice=" + salePrice + ", totalPrice=" + totalPrice + ", point=" + point + ", totalPoint="
				+ totalPoint + ", imageList=" + imageList + "]";
	}

	


}
