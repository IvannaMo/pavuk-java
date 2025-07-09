package com.needleandstitch.pavuk.model;


public class OrderRequest {
	private User user;
	private ClothingItem clothingItem;
	private ShippingInfo shippingInfo;
	
	
	public OrderRequest() {}
	
	public OrderRequest(User user, ClothingItem clothingItem, ShippingInfo shippingInfo) {
        this.user = user;
        this.clothingItem = clothingItem;
        this.shippingInfo = shippingInfo;
    }
	
	
	public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public ClothingItem getClothingItem() {
        return clothingItem;
    }

    public void setClothingItem(ClothingItem clothingItem) {
        this.clothingItem = clothingItem;
    }
    
    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }
}
