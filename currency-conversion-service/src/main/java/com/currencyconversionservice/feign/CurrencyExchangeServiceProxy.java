package com.currencyconversionservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.currencyconversionservice.entities.CurrencyConversionBean;

@FeignClient(name = "currency-exchange-service", url = "http://localhost:8000")
public interface CurrencyExchangeServiceProxy {

	@GetMapping("/exchange/from/{from}/to/{to}")
	public CurrencyConversionBean getExchange(@PathVariable String from, @PathVariable String to);
	
}
