package com.bank.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
@Entity
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bankName;
    private String ifscCode;
    @OneToMany(
        mappedBy = "bank",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY
    )
    @JsonIgnoreProperties("bank")
    private List<Account> accounts;
    public Bank() {
    }
    public Bank(Long id, String bankName, String ifscCode,
                List<Account> accounts) {

        this.id = id;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.accounts = accounts;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getIfscCode() {
        return ifscCode;
    }
    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
    public List<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}