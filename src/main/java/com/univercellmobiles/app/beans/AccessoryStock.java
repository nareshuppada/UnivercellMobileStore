package com.univercellmobiles.app.beans;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACCESSORYSTOCK")
public class AccessoryStock {

	
	@Id
	@GeneratedValue
	private int accStockId;
	private Date arrivalDate;
	private String accModel;
	private String phmodelName;
	private String accType;
	private Date soldDate;
	private String desription;
	private Float margin;
	private Float dp;
	private Float sp;
	private int quantity;
	private int available;
	
	
	
	/**
	 * @return the available
	 */
	public int getAvailable() {
		return available;
	}
	/**
	 * @param available the available to set
	 */
	public void setAvailable(int available) {
		this.available = available;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Date getSoldDate() {
		return soldDate;
	}
	public void setSoldDate(Date soldDate) {
		this.soldDate = soldDate;
	}
	/**
	 * @return the accStockId
	 */
	public int getAccStockId() {
		return accStockId;
	}
	/**
	 * @param accStockId the accStockId to set
	 */
	public void setAccStockId(int accStockId) {
		this.accStockId = accStockId;
	}
	
	
	/**
	 * @return the arrivalDate
	 */
	/*public Timestamp getArrivalDate() {
		return arrivalDate;
	}
	*//**
	 * @param arrivalDate the arrivalDate to set
	 *//*
	public void setArrivalDate(Timestamp arrivalDate) {
		this.arrivalDate = arrivalDate;
	}*/
	/**
	 * @return the accModel
	 */
	public String getAccModel() {
		return accModel;
	}
	/**
	 * @return the accType
	 */
	public String getAccType() {
		return accType;
	}
	/**
	 * @param accType the accType to set
	 */
	public void setAccType(String accType) {
		this.accType = accType;
	}
	/**
	 * @param accModel the accModel to set
	 */
	public void setAccModel(String accModel) {
		this.accModel = accModel;
	}
	/**
	 * @return the phmodelName
	 */
	public String getPhmodelName() {
		return phmodelName;
	}
	
	public String getDesription() {
		return desription;
	}
	public void setDesription(String desription) {
		this.desription = desription;
	}
	/**
	 * @param phmodelName the phmodelName to set
	 */
	public void setPhmodelName(String phmodelName) {
		this.phmodelName = phmodelName;
	}
	/**
	 * @return the soldDate
	 */
	
	/*public Timestamp getSoldDate() {
		return soldDate;
	}
	*//**
	 * @param soldDate the soldDate to set
	 *//*
	public void setSoldDate(Timestamp soldDate) {
		this.soldDate = soldDate;
	}*/
	/**
	 * @return the desc
	 */
	
	/**
	 * @return the margin
	 */
	public Float getMargin() {
		return margin;
	}
	/**
	 * @param f the margin to set
	 */
	public void setMargin(Float margin) {
		this.margin = margin;
	}
	/**
	 * @return the dp
	 */
	public Float getDp() {
		return dp;
	}
	/**
	 * @param dp the dp to set
	 */
	public void setDp(Float dp) {
		this.dp = dp;
	}
	/**
	 * @return the sp
	 */
	public Float getSp() {
		return sp;
	}
	/**
	 * @param sp the sp to set
	 */
	public void setSp(Float sp) {
		this.sp = sp;
	}
	@Override
	public String toString() {
		return "AccessoryStock [accStockId=" + accStockId + ", accModel="
				+ accModel + ", phmodelName=" + phmodelName + ", desription="
				+ desription + ", margin=" + margin + ", dp=" + dp + ", sp="
				+ sp + "]";
	}
	
	
	
}
