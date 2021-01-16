package com.currencylimitservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currencylimitservice.LimitServicePropertyConfiguration;
import com.currencylimitservice.entities.LimitConfiguration;

@RestController
@RequestMapping("/limits")
public class LimitConfigurationController {
	
	@Autowired
	private LimitServicePropertyConfiguration limitServicePropertyConfiguration;

	/*
	@GetMapping("/hardcoded")
	public LimitConfiguration getLimits() {
		//Create the hard coded object for the LimitConfiguration
		return new LimitConfiguration(1, 999);
	}*/
	
	@GetMapping
	public LimitConfiguration getLimitsFromApplicationProperties() {
		//Create the hard coded object for the LimitConfiguration
		return new LimitConfiguration(limitServicePropertyConfiguration.getMinimum(), limitServicePropertyConfiguration.getMaximum());
	}
	
}
