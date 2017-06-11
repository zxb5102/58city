package com.collect.specific.bean;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HouseMsg {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(length=500)
	private String link;
	private String name;
	
    @Column( name = "udate" )
	private Date update;
	private HouseBase housebase = new HouseBase();
	private HouseDesc housedesc = new HouseDesc();
	
	   @Embedded
	    @AttributeOverrides({
	    	@AttributeOverride(
		            name = "price",
		            column = @Column( name = "eprice" )
		        ),
	    	@AttributeOverride(
		            name = "area",
		            column = @Column( name = "earea" )
		        ),
	    	@AttributeOverride(
		            name = "style",
		            column = @Column( name = "estyle" )
		        )
	    }
	    )
	private EstateDesc estatedesc = new EstateDesc();

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}

	public HouseBase getHousebase() {
		return housebase;
	}

	public void setHousebase(HouseBase housebase) {
		this.housebase = housebase;
	}

	public HouseDesc getHousedesc() {
		return housedesc;
	}

	public void setHousedesc(HouseDesc housedesc) {
		this.housedesc = housedesc;
	}

	public EstateDesc getEstatedesc() {
		return estatedesc;
	}

	public void setEstatedesc(EstateDesc estatedesc) {
		this.estatedesc = estatedesc;
	}

	@Override
	public String toString() {
		return "HouseMsg [id=" + id + ", link=" + link + ", name=" + name + ", update=" + update + ", housebase="
				+ housebase + ", housedesc=" + housedesc + ", estatedesc=" + estatedesc + "]";
	}

}
