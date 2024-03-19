package com.master.springboot.series.accounts.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "accounts")
public class AccountsApiIDto {
    String env;
    String version;
}
