package com.currencylimitservice.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currencylimitservice.entities.LimitConfiguration;

@RestController
@RequestMapping("limit-service")
public class LimitConfigurationController {

	@GetMapping
	public LimitConfiguration getLimits() {
		return new LimitConfiguration(1, 999);
	}
	
}
