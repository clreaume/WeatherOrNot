package com.gc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "Item")


public class Item {

	private InHamp inHamper;
	private int userId;
	private String type;
	private Category cat;
	private String description;
	private String imageURL;
	private String itemId;
	
	public Item() {
		
	}
	
	//itemID removed from below constructor since it's auto-generated
	public Item(InHamp inHamper, int userId, String type, String description, String imageURL, Category cat) {
		super();
		this.inHamper = inHamper;
		this.userId = userId;
		this.type = type;
		this.description = description;
		this.imageURL = imageURL;
		this.cat = cat;
	}
	
	
	@Column
	public InHamp getInHamp() {
		return inHamper;
	}
	public void setInHamp(InHamp inHamper) {
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
	
	@Column
	public Category getCategory() {
		return cat;
	}

	public void setCategory(Category cat) {
		this.cat = cat;
	}
	
	
	
}
