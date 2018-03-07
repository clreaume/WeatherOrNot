package com.gc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "Item")

public class Item {

	private boolean inHamper;
	private int userId;
	private String type;
	private enum category{TOP, BOTTOM, DRESS, SHOES, SWEATER, OUTERWEAR, ACCESSORIES};
	private String description;
	private String imageURL;
	private String itemId;
	
	public Item() {
		
	}
	
	public Item(boolean inHamper, int userId, String type, String description, String imageURL, String itemId) {
		super();
		this.inHamper = inHamper;
		this.userId = userId;
		this.type = type;
		this.description = description;
		this.imageURL = imageURL;
		this.itemId = itemId;
	}
	
	
	@Column
	public boolean isInHamper() {
		return inHamper;
	}
	public void setInHamper(boolean inHamper) {
		this.inHamper = inHamper;
	}

	@Column
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	@Id
	@GeneratedValue
	@Column
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	
	
}
