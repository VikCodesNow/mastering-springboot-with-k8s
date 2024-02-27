package com.master.springboot.series.accounts.service.impl;

import com.master.springboot.series.accounts.constants.AccountsConstants;
import com.master.springboot.series.accounts.dto.AccountsDTO;
import com.master.springboot.series.accounts.dto.CustomerDTO;
import com.master.springboot.series.accounts.entites.Accounts;
import com.master.springboot.series.accounts.entites.Customer;
import com.master.springboot.series.accounts.exceptions.AccountNotFound;
import com.master.springboot.series.accounts.exceptions.CustomerAlreadyExists;
import com.master.springboot.series.accounts.exceptions.CustomerNotFound;
import com.master.springboot.series.accounts.repository.AccountsRepository;
import com.master.springboot.series.accounts.repository.CustomerRepository;
import com.master.springboot.series.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AccountService implements IAccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDTO customerDTO) {
       Optional<Customer> existingCustomer =  customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
       if(existingCustomer.isPresent()) {
           throw new CustomerAlreadyExists("Customer Already Exists");
       }
        Customer customer = CustomerDTO.mapToEntity(customerDTO,new Customer());
        Customer savedCustomer =   customerRepository.save(customer);
        Accounts account = new Accounts();
        account.setCustomerId(savedCustomer.getCustomerId());
        account.setAccountType(AccountsConstants.SAVINGS_ACCOUNT);
        account.setBranchAddress(AccountsConstants.BRANCH_ADDRESS);
        accountsRepository.save(account);
    }

    @Override
    public CustomerDTO fetchCustomer(String mobileNumber) {

       Customer customer =  customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new CustomerNotFound(String.format("Couldn't find customer with mobile number %s",mobileNumber)));
       Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()->new AccountNotFound(String.format("Couldn't find Account for Customer %s",customer.getCustomerName())));
       CustomerDTO dto = CustomerDTO.mapToDTO(customer,new CustomerDTO());
       dto.setAccountsDTO(AccountsDTO.mapToDTO(accounts,new AccountsDTO()));
       return dto;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) {
        boolean isUpdated = false;
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
        if(accountsDTO!=null) {
           Accounts account = accountsRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(()->new AccountNotFound(String.format("Couldn't find account with account number %d",accountsDTO.getAccountNumber())));
            AccountsDTO.mapToEntity(accountsDTO,account);
            Customer customer = customerRepository.findById(account.getCustomerId()).orElseThrow(()-> new CustomerNotFound(String.format("Couldn't find customer with account number %d",account.getAccountNumber())));
            CustomerDTO.mapToEntity(customerDTO,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteCustomer(String mobileNumber) {
        try
        {
            Customer customer =  customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new CustomerNotFound(String.format("Couldn't find customer with mobile number %s",mobileNumber)));
            accountsRepository.deleteByCustomerId(customer.getCustomerId());
            customerRepository.deleteById(customer.getCustomerId());
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
