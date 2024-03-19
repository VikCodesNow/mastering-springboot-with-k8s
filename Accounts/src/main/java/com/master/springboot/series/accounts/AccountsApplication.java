package com.master.springboot.series.accounts;

import com.master.springboot.series.accounts.dto.AccountsApiIDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsApiIDto.class})
@OpenAPIDefinition(info = @Info(
		title = "Account Service",
		description = "Creating and updating of customer Accounts",
		version = "0.0.1",
		contact = @Contact(name = "Vikram Baliga",email = "vikram.baliga21@gmail.com")
))
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
