package com.master.springboot.series.accounts.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class ResponseDTO {
    private HttpStatus statusCode;
    private String statusMsg;
}
