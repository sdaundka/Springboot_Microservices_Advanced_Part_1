package com.currencyexchangeservice.resource;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.currencyexchangeservice.entities.ExchangeValue;
import com.currencyexchangeservice.jpa.CurrencyExchangeJPARepository;

@RestController
@RequestMapping("/exchange")
public class CurrencyExchangeResource {

	@Autowired
	private Environment environment;

	
	@Autowired
	private CurrencyExchangeJPARepository jpaRepository;
	
	/*
	static {
		jpaRepository.save(new ExchangeValue(1, "USD", "INR", BigDecimal.valueOf(73)));
		jpaRepository.save(new ExchangeValue(2, "AUD", "INR", BigDecimal.valueOf(48)));
		jpaRepository.save(new ExchangeValue(3, "EUR", "INR", BigDecimal.valueOf(85)));
	}*/
	
	@GetMapping
	public ExchangeValue getExchange() {
		String port = environment.getProperty("local.server.port");
		return new ExchangeValue(1, "USD", "INR", BigDecimal.valueOf(65), port);
	}
	
	@GetMapping("/from/{from}/to/{to}")
	public ExchangeValue getExchange(@PathVariable String from, @PathVariable String to) {
		String port = environment.getProperty("local.server.port");
		ExchangeValue exchangeValue = jpaRepository.findByFromAndTo(from, to);
		exchangeValue.setPort(port);
		return exchangeValue;
	}
	
}
