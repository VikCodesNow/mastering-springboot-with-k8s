package com.master.springboot.series.accounts.controller;


import com.master.springboot.series.accounts.dto.CustomerDTO;
import com.master.springboot.series.accounts.dto.ResponseDTO;
import com.master.springboot.series.accounts.service.IAccountService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/account",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    private IAccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        ResponseDTO dto = ResponseDTO.builder().statusCode(HttpStatus.CREATED).statusMsg("Created Successfully").build();
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @GetMapping("/fetch-customer")
    public ResponseEntity<CustomerDTO> fetchCustomer(@RequestParam String mobileNumber) {
    CustomerDTO dto =  accountService.fetchCustomer(mobileNumber);
    return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
