package com.univercellmobiles.app.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="ACCESSORY_SALES")
public class AccessorySales {
	@GeneratedValue
    @Id
	private int saleId;
	private Float salePrice;
	private Float dp;
	private Float margin;
	private Date salesDate;
	private String invoiceId;
	private Float discount;
	private String phoneModel;
	private int stockId;
	private int qty;
	private int custId;
	private String custName;
	private String custContact;
	private String Distributor;
	private String saleType;
	private Float vat;
	private String offer;
	private String description;
	
	
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	
	
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	/**
	 * @return the salePrice
	 */
	public Float getSalePrice() {
		return salePrice;
	}
	/**
	 * @param salePrice the salePrice to set
	 */
	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
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
	 * @return the margin
	 */
	public Float getMargin() {
		return margin;
	}
	/**
	 * @param margin the margin to set
	 */
	public void setMargin(Float margin) {
		this.margin = margin;
	}
	/**
	 * @return the salesDate
	 */
	public Date getSalesDate() {
		return salesDate;
	}
	/**
	 * @param salesDate the salesDate to set
	 */
	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}
	/**
	 * @return the invoiceId
	 */
	public String getInvoiceId() {
		return invoiceId;
	}
	/**
	 * @param invoiceId the invoiceId to set
	 */
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	/**
	 * @return the discount
	 */
	public Float getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sales [saleId=" + saleId + ", salePrice=" + salePrice + ", dp="
				+ dp + ", margin=" + margin + ", salesDate=" + salesDate
				+ ", invoiceId=" + invoiceId + ", discount=" + discount + "]";
	}
	/**
	 * @return the phoneModel
	 */
	public String getPhoneModel() {
		return phoneModel;
	}
	/**
	 * @param phoneModel the phoneModel to set
	 */
	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}
	/**
	 * @return the custId
	 */
	public int getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}
	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * @return the custContact
	 */
	public String getCustContact() {
		return custContact;
	}
	/**
	 * @param custContact the custContact to set
	 */
	public void setCustContact(String custContact) {
		this.custContact = custContact;
	}
	/**
	 * @return the vat
	 */
	public Float getVat() {
		return vat;
	}
	/**
	 * @param vat the vat to set
	 */
	public void setVat(Float vat) {
		this.vat = vat;
	}
	/**
	 * @return the distributor
	 */
	public String getDistributor() {
		return Distributor;
	}
	/**
	 * @param distributor the distributor to set
	 */
	public void setDistributor(String distributor) {
		Distributor = distributor;
	}
	/**
	 * @return the offer
	 */
	public String getOffer() {
		return offer;
	}
	/**
	 * @param offer the offer to set
	 */
	public void setOffer(String offer) {
		this.offer = offer;
	}
	

	/**
	 * @return the saleType
	 */
	public String getSaleType() {
		return saleType;
	}
	/**
	 * @param saleType the saleType to set
	 */
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
