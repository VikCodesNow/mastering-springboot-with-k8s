package com.master.springboot.series.accounts.service;

import com.master.springboot.series.accounts.dto.CustomerDTO;

public interface IAccountService {
    void createAccount(CustomerDTO customerDTO);

    CustomerDTO fetchCustomer(String mobileNumber);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);
}
