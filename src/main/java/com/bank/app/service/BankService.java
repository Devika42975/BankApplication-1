package com.bank.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.app.model.Account;
import com.bank.app.model.Bank;
import com.bank.app.repository.AccountRepository;
import com.bank.app.repository.BankRepository;
@Service
public class BankService {
	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private AccountRepository accountRepository;
	public Bank saveBank(Bank bank) {
		return bankRepository.save(bank);
	}

	public List<Bank> getAllBanks() {
		return bankRepository.findAll();
	}

	public Account addAccountToBank(Long bankId, Account account) {
	    Bank bank = bankRepository.findById(bankId)
	            .orElseThrow(() -> new RuntimeException("Bank not found"));

	    account.setBank(bank);          // ⭐ This line is missing

	    return accountRepository.save(account);
	}
}