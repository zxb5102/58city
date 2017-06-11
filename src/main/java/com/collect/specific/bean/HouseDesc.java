package com.collect.specific.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class HouseDesc {

	private String configure;
	@Column(length=500)
	private String advantage;
	@Column(name="required",length=500)
	private String require;
	@Column(length=1000)
	private String description;

	public String getConfigure() {
		return configure;
	}

	public void setConfigure(String configure) {
		this.configure = configure;
	}

	public String getAdvantage() {
		return advantage;
	}

	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}

	public String getRequire() {
		return require;
	}

	public void setRequire(String require) {
		this.require = require;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "HouseDesc [configure=" + configure + ", advantage=" + advantage + ", require=" + require
				+ ", description=" + description + "]";
	}

	
}
