package com.master.springboot.series.accounts.dto;


import com.master.springboot.series.accounts.entites.Accounts;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountsDTO {
    private Long accountNumber;
    @NotEmpty(message = "Account Type cannot be null or empty")
    private String accountType;
    @NotEmpty(message = "Address cannot be null or empty")
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
