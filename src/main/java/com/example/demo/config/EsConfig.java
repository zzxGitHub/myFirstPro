package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {
	
	@Value("${esConfig.switch}")
	private boolean esSwitch;

	public boolean isEsSwitch() {
		return esSwitch;
	}

	public void setEsSwitch(boolean esSwitch) {
		this.esSwitch = esSwitch;
	}
	
	
}
