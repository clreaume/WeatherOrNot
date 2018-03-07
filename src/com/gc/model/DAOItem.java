package com.gc.model;

import java.util.ArrayList;

public interface DAOItem {
	
	void addItem(Item item);
	
	ArrayList<Item> getAllItems();
	
	void deleteItem(Item item);
	
	ArrayList<Item> getOutfit();

}
