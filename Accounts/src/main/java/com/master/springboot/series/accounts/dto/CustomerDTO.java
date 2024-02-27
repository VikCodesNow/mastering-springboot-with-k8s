package com.master.springboot.series.accounts.dto;



import com.master.springboot.series.accounts.entites.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO
{
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5,max = 30,message = "Length of username should be between 5 and 30")
    private String customerName;
    @Pattern(regexp = "$|[0-9]{10}",message = "Number should be 10 digit")
    private String mobileNumber;
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email is not valid ")
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
