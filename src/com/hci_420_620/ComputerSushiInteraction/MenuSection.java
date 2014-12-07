package com.hci_420_620.ComputerSushiInteraction;

import java.util.ArrayList;

public class MenuSection {
	String sectionName;
	ArrayList<MenuItem> itemList;

	public MenuSection(String sectionName) {
		super();
		this.sectionName = sectionName;
		itemList = new ArrayList<MenuItem>();
	}
	
	public String getSectionName() {
		return sectionName;
	}
	
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public void addItem(MenuItem menuItem){
		this.itemList.add(menuItem);
	}
	

	public int getItemCount() {
		return itemList.size();
	}

	public MenuItem getItem(int childPosition) {
		return itemList.get(childPosition);
	}
}
