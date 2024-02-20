package com.master.springboot.series.accounts.dto;



import com.master.springboot.series.accounts.entites.Customer;
import lombok.Data;

@Data
public class CustomerDTO
{
    private String customerName;
    private String mobileNumber;
    private String customerEmail;
    private AccountsDTO accountsDTO;

    public static Customer mapToEntity(CustomerDTO customerDTO, Customer customer) {
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setCustomerEmail(customerDTO.getCustomerEmail());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }

    public static CustomerDTO mapToDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setCustomerEmail(customer.getCustomerEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }
}
