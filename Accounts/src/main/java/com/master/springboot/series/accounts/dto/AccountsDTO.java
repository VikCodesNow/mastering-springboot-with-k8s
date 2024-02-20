package com.master.springboot.series.accounts.dto;


import com.master.springboot.series.accounts.entites.Accounts;
import lombok.Data;

@Data
public class AccountsDTO {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;


    public static Accounts mapToEntity(AccountsDTO accountsDTO,Accounts accounts) {
        accounts.setAccountNumber(accountsDTO.getAccountNumber());
        accounts.setAccountType(accountsDTO.getAccountType());
        accounts.setBranchAddress(accountsDTO.getBranchAddress());
        return accounts;
    }

    public static AccountsDTO mapToDTO(Accounts accounts,AccountsDTO accountsDTO) {
        accountsDTO.setAccountNumber(accounts.getAccountNumber());
        accountsDTO.setAccountType(accounts.getAccountType());
        accountsDTO.setBranchAddress(accounts.getBranchAddress());
        return accountsDTO;
    }
}
