package com.master.springboot.series.accounts.controller;


import com.master.springboot.series.accounts.dto.CustomerDTO;
import com.master.springboot.series.accounts.dto.ResponseDTO;
import com.master.springboot.series.accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(value = "/api/v1/account",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    @Autowired
    private IAccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        accountService.createAccount(customerDTO);
        ResponseDTO dto = ResponseDTO.builder().statusCode(HttpStatus.CREATED).statusMsg("Created Successfully").build();
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @GetMapping("/fetch-customer")
    public ResponseEntity<CustomerDTO> fetchCustomer(@RequestParam
                                                     @Pattern(regexp = "$|[0-9]{10}",message = "Number should be 10 digit")
                                                     String mobileNumber)
    {
    CustomerDTO dto =  accountService.fetchCustomer(mobileNumber);
    return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCustomer(@Valid  @RequestBody CustomerDTO customerDT) {
      boolean res =   accountService.updateCustomer(customerDT);
      if(res) {
          return  new ResponseEntity<>(ResponseDTO.builder().statusCode(HttpStatus.OK).statusMsg("Updated Successfully").build(),HttpStatus.OK);
      }
        return  new ResponseEntity<>(ResponseDTO.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR).statusMsg("Error Occurred").build(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCustomer(@RequestParam
                                                      @Pattern(regexp = "$|[0-9]{10}",message = "Number should be 10 digit")
                                                      String mobileNumber)
    {
        boolean res =   accountService.deleteCustomer(mobileNumber);
        if(res) {
            return  new ResponseEntity<>(ResponseDTO.builder().statusCode(HttpStatus.OK).statusMsg("Deleted Successfully").build(),HttpStatus.OK);
        }
        return  new ResponseEntity<>(ResponseDTO.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR).statusMsg("Error Occurred").build(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
