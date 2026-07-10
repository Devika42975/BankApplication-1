package com.bank.app.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.app.dto.AccountDTO;
import com.bank.app.model.Account;
import com.bank.app.model.Bank;
import com.bank.app.service.BankService;

@RestController
@RequestMapping("/api/banks")
@CrossOrigin(origins="*")
public class BankController{
	@Autowired
	private BankService bankService;
	@PostMapping
	public Bank createBank(@RequestBody Bank bank) {
		return bankService.saveBank(bank);
	}
	@GetMapping
	public List<Bank> getAllBanks(){
		return bankService.getAllBanks();
	}
	@PostMapping("/{bankId}/accounts")
	public Account addAccount(@PathVariable Long bankId,@RequestBody AccountDTO accountDTO) {
		Account account=new Account();
		account.setAccountHolderName(accountDTO.getAccountHolderName());
		account.setBalance(accountDTO.getBalance());
		return bankService.addAccountToBank(bankId,account);
	}
	private Object getAccountHolderName() {
		// TODO Auto-generated method stub
		return null;
	}
}