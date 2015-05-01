package com.univercellmobiles.app.beans;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FUNDS")
public class FundStatus {
	
	@Id
	@GeneratedValue
	private int statusId;
	private float investment;
	private float expense;
	private float stockValue;
	private float profit;
	private float assets;
	private float cash;
	private float univercellfunds;
	private float returns;
	private float fundsout;
	private float deposits;
	private Date today;
	private float accStockValue;
	private float accProfit;
	private float phoneSale;//today
	private float accSale;//today
	private float recharges;
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public float getInvestment() {
		return investment;
	}
	public void setInvestment(float investment) {
		this.investment = investment;
	}
	public float getExpense() {
		return expense;
	}
	public void setExpense(float expense) {
		this.expense = expense;
	}
	public float getStockValue() {
		return stockValue;
	}
	public void setStockValue(float stockValue) {
		this.stockValue = stockValue;
	}
	public float getProfit() {
		return profit;
	}
	public void setProfit(float profit) {
		this.profit = profit;
	}
	public float getAssets() {
		return assets;
	}
	public void setAssets(float assets) {
		this.assets = assets;
	}
	public float getCash() {
		return cash;
	}
	public void setCash(float cash) {
		this.cash = cash;
	}
	public float getUnivercellfunds() {
		return univercellfunds;
	}
	public void setUnivercellfunds(float univercellfunds) {
		this.univercellfunds = univercellfunds;
	}
	public float getReturns() {
		return returns;
	}
	public void setReturns(float returns) {
		this.returns = returns;
	}
	public float getFundsout() {
		return fundsout;
	}
	public void setFundsout(float fundsout) {
		this.fundsout = fundsout;
	}
	public float getDeposits() {
		return deposits;
	}
	public void setDeposits(float deposits) {
		this.deposits = deposits;
	}
	/**
	 * @return the today
	 */
	public Date getToday() {
		return today;
	}
	/**
	 * @param today the today to set
	 */
	public void setToday(Date today) {
		this.today = today;
	}
	/**
	 * @return the accStockValue
	 */
	public float getAccStockValue() {
		return accStockValue;
	}
	/**
	 * @param accStockValue the accStockValue to set
	 */
	public void setAccStockValue(float accStockValue) {
		this.accStockValue = accStockValue;
	}
	/**
	 * @return the accProfit
	 */
	public float getAccProfit() {
		return accProfit;
	}
	/**
	 * @param accProfit the accProfit to set
	 */
	public void setAccProfit(float accProfit) {
		this.accProfit = accProfit;
	}
	/**
	 * @return the phoneSale
	 */
	public float getPhoneSale() {
		return phoneSale;
	}
	/**
	 * @param phoneSale the phoneSale to set
	 */
	public void setPhoneSale(float phoneSale) {
		this.phoneSale = phoneSale;
	}
	/**
	 * @return the accSale
	 */
	public float getAccSale() {
		return accSale;
	}
	/**
	 * @param accSale the accSale to set
	 */
	public void setAccSale(float accSale) {
		this.accSale = accSale;
	}
	/**
	 * @return the recharges
	 */
	public float getRecharges() {
		return recharges;
	}
	/**
	 * @param recharges the recharges to set
	 */
	public void setRecharges(float recharges) {
		this.recharges = recharges;
	}
	
	
	
	
	

}
