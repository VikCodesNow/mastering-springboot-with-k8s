package com.master.springboot.series.accounts.dto;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
public record AccountsAPIDTO(String env,String version) {
}
