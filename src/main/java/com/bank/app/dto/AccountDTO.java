package com.bank.app.dto;

public class AccountDTO {
	private String accountHolderName;
	private Double balance;
	public String getAccountHolderName() {
		return accountHolderName;
	}
	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName=accountHolderName;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance=balance;
	}
}