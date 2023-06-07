package com.mino.domain;

//주문결제시 이용되는 DTO
public class OrderDTO {
	
	//주문번호
	private String orderId;
	
	//물품 번호
	private int itemId;
	
	//물품 수량
	private int itemCount;
	
	//shop_orderItemId
	private int orderItemId;
	
	//물품 가격
	private int itemPrice;
	
	//물품 할인
	private double itemDiscount;
	
	//물품 구매시 획득 포인트
	private int savePoint;
	
	/* DB에 저장되지 않는 데이터 */

	//할인 적용된 가격
	private int salePrice;
	
	//총 가격 (할인 적용된 가격 * 주문 수량)
	private int totalPrice;
	
	//총 포인트 (savePoint * 수량)
	private int totalSavePoint;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
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

	public int getSavePoint() {
		return savePoint;
	}

	public void setSavePoint(int savePoint) {
		this.savePoint = savePoint;
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

	public int getTotalSavePoint() {
		return totalSavePoint;
	}

	public void setTotalSavePoint(int totalSavePoint) {
		this.totalSavePoint = totalSavePoint;
	}

	public void initSaleTotal() {
		this.salePrice = (int) (this.itemPrice * (1-this.itemDiscount));
		this.totalPrice = this.salePrice*this.itemCount;
		this.savePoint = (int)(Math.floor(this.salePrice*0.05));
		this.totalSavePoint =this.savePoint * this.itemCount;
	}
	
	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", itemId=" + itemId + ", itemCount=" + itemCount + ", orderItemId="
				+ orderItemId + ", itemPrice=" + itemPrice + ", itemDiscount=" + itemDiscount + ", savePoint="
				+ savePoint + ", salePrice=" + salePrice + ", totalPrice=" + totalPrice + ", totalSavePoint="
				+ totalSavePoint + "]";
	}


	
	
}
