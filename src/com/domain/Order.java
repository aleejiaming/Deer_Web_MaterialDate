package com.domain;

public class Order {
	private int order_Id;
	private int cus_Id;
	private String customer_Name;
	private String customer_Num;
	private String customer_Addres;
	private int mat_Id;
	private String material_Name;
	private String material_Type;	
	private int sales_Amount;
	
	
	
	public int getCus_Id() {
		return cus_Id;
	}
	public void setCus_Id(int cus_Id) {
		this.cus_Id = cus_Id;
	}
	public String getCustomer_Addres() {
		return customer_Addres;
	}
	public void setCustomer_Address(String customer_Addres) {
		this.customer_Addres = customer_Addres;
	}
	public int getOrder_Id() {
		return order_Id;
	}
	public void setOrder_Id(int order_Id) {
		this.order_Id = order_Id;
	}
	public String getCustomer_Name() {
		return customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}
	public String getCustomer_Num() {
		return customer_Num;
	}
	public void setCustomer_Num(String customer_Num) {
		this.customer_Num = customer_Num;
	}
	public String getMaterial_Name() {
		return material_Name;
	}
	public void setMaterial_Name(String material_Name) {
		this.material_Name = material_Name;
	}
	public String getMaterial_Type() {
		return material_Type;
	}
	public void setMaterial_Type(String material_Type) {
		this.material_Type = material_Type;
	}
	public int getMat_Id() {
		return mat_Id;
	}
	public void setMat_Id(int mat_Id) {
		this.mat_Id = mat_Id;
	}
	public int getSales_Amount() {
		return sales_Amount;
	}
	public void setSales_Amount(int sales_Amount) {
		this.sales_Amount = sales_Amount;
	}
	
	
}
