package com.domain;

public class Material {
	private String CustomerName;
	private String CustomerNum;
	private int material_Id;
	private String material_Name;
	private int material_Amount ;
	private String material_Type;
	
	
	
	public int getMaterial_Id() {
		return material_Id;
	}
	public void setMaterial_Id(int material_Id) {
		this.material_Id = material_Id;
	}
	public String getMaterial_Name() {
		return material_Name;
	}
	public void setMaterial_Name(String material_Name) {
		this.material_Name = material_Name;
	}
	public int getMaterial_Amount() {
		return material_Amount;
	}
	public void setMaterial_Amount(int material_Amount) {
		this.material_Amount = material_Amount;
	}
	public String getMaterial_Type() {
		return material_Type;
	}
	public void setMaterial_Type(String material_Type) {
		this.material_Type = material_Type;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		this.CustomerName = customerName;
	}
	public String getCustomerNum() {
		return CustomerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.CustomerNum = customerNum;
	}
	
	
}
