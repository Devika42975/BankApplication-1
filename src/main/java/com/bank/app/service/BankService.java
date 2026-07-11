package com.bank.app.service;
import com.bank.app.exception.ResourceNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

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
	public Bank getBankById(Long id) {
	    return bankRepository.findById(id)
	    		.orElseThrow(() -> new ResourceNotFoundException("Bank not found with id: " + id));
	}
	public void deleteBank(Long id) {
		Bank bank=bankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bank not found with id: " + id));
		bankRepository.delete(bank);
	}
	public List<Bank> getAllBanks() {
		return bankRepository.findAll();
	}
	public Bank updateBank(Long id, Bank bank) {
	    Bank existing = bankRepository.findById(id)
	    		.orElseThrow(() -> new ResourceNotFoundException("Bank not found with id: " + id));

	    existing.setBankName(bank.getBankName());
	    existing.setIfscCode(bank.getIfscCode());
	    return bankRepository.save(existing);
	}

	public Account addAccountToBank(Long bankId, Account account) {
	    Bank bank = bankRepository.findById(bankId)
	    		.orElseThrow(() -> new ResourceNotFoundException("Bank not found with id: " + bankId));

	    account.setBank(bank);          // ⭐ This line is missing

	    return accountRepository.save(account);
	}
}