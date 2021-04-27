package com.github.hanpyo.config.property;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("hanpyo.login")
@Getter
@Setter
public class LoginProperties {

	@NotBlank
	private String loginPage;
}
