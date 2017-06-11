package com.collect.specific.bean;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class EstateDesc {

	private Date year;
	private String style;
	private String company;
	private Double price;
	private String area;

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "EstateDesc [year=" + year + ", style=" + style + ", company=" + company + ", price=" + price + ", area="
				+ area + "]";
	}

	
}
