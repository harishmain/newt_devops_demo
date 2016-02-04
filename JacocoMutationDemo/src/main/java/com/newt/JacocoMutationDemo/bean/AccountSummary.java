package com.newt.JacocoMutationDemo.bean;

import java.util.Date;

public class AccountSummary {
	
	private int account_no;
	
	private String account_name;
	
	private double previos_balance;
	
	private double current_balance;
	
	private double bill_amount;
	
	private Date bill_date;

	public int getAccount_no() {
		return account_no;
	}

	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public double getPrevios_balance() {
		return previos_balance;
	}

	public void setPrevios_balance(double previos_balance) {
		this.previos_balance = previos_balance;
	}

	public double getCurrent_balance() {
		return current_balance;
	}

	public void setCurrent_balance(double current_balance) {
		this.current_balance = current_balance;
	}

	public double getBill_amount() {
		return bill_amount;
	}

	public void setBill_amount(double bill_amount) {
		this.bill_amount = bill_amount;
	}

	public Date getBill_date() {
		return bill_date;
	}

	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}
	
	
}
