package com.KudosApp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class comboModel {
	
	private SimpleStringProperty brandName;
	private SimpleStringProperty itemName;
	public comboModel(String brandName, String itemName){
		this.brandName = new SimpleStringProperty(brandName);
		this.itemName = new SimpleStringProperty(itemName);
	}
	public String getbrandName()
	{
		return brandName.get();
	}
	public void setbrandName(String Value)
	{
		brandName.set(Value);
	}
	public StringProperty brandNameProperty()
	{
		return brandName;
	}
	
	public String getitemName()
	{
		return itemName.get();
	}
	public void setitemName(String Value)
	{
		itemName.set(Value);
	}
	public StringProperty itemNameProperty()
	{
		return itemName;
	}
}
