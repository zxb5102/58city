package com.collect.specific.bean;

import javax.persistence.Embeddable;

@Embeddable
public class HouseBase {
	
	private int price;
	private String payStyle;
	private String rentStyle;
	private String houseStyle;
	private int size;
	private String decorateStyle;
	private String orien;
	private int totalFloor;
	private int currFloor;
	private String estate;
	private String area;
	private String location;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPayStyle() {
		return payStyle;
	}
	public void setPayStyle(String payStyle) {
		this.payStyle = payStyle;
	}
	public String getRentStyle() {
		return rentStyle;
	}
	public void setRentStyle(String rentStyle) {
		this.rentStyle = rentStyle;
	}
	public String getHouseStyle() {
		return houseStyle;
	}
	public void setHouseStyle(String houseStyle) {
		this.houseStyle = houseStyle;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getDecorateStyle() {
		return decorateStyle;
	}
	public void setDecorateStyle(String decorateStyle) {
		this.decorateStyle = decorateStyle;
	}
	public String getOrien() {
		return orien;
	}
	public void setOrien(String orien) {
		this.orien = orien;
	}
	public int getTotalFloor() {
		return totalFloor;
	}
	public void setTotalFloor(int totalFloor) {
		this.totalFloor = totalFloor;
	}
	public int getCurrFloor() {
		return currFloor;
	}
	public void setCurrFloor(int currFloor) {
		this.currFloor = currFloor;
	}
	public String getEstate() {
		return estate;
	}
	public void setEstate(String estate) {
		this.estate = estate;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "HouseBase [price=" + price + ", payStyle=" + payStyle + ", rentStyle=" + rentStyle + ", houseStyle="
				+ houseStyle + ", size=" + size + ", decorateStyle=" + decorateStyle + ", orien=" + orien
				+ ", totalFloor=" + totalFloor + ", currFloor=" + currFloor + ", estate=" + estate + ", area=" + area
				+ ", location=" + location + "]";
	}

	
}
