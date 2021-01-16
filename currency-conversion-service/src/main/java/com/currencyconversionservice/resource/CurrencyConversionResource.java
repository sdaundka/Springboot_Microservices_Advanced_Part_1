package com.currencyconversionservice.resource;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.currencyconversionservice.entities.CurrencyConversionBean;
import com.currencyconversionservice.feign.CurrencyExchangeServiceProxy;

@RestController
@RequestMapping("convert")
public class CurrencyConversionResource {
	
	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

	@GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		Map<String,String> map = new HashMap<>();
		map.put("from", from);
		map.put("to", to);
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/exchange/from/{from}/to/{to}", CurrencyConversionBean.class, map);
		CurrencyConversionBean currencyConversionBean = null;
		if(responseEntity != null)
			currencyConversionBean = responseEntity.getBody();
		
		if(currencyConversionBean != null) {
			currencyConversionBean.setQuantity(quantity);
			BigDecimal conversionMultiple = currencyConversionBean.getConversionMultiple();
			currencyConversionBean.setTotalCalculatedAmount(conversionMultiple.multiply(quantity));
		}
		
		return currencyConversionBean;
	}
	
	@GetMapping(value = "/feign/from/{from}/to/{to}/quantity/{quantity}", produces = "application/json")
	public CurrencyConversionBean convertCurrencyUsingFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		CurrencyConversionBean currencyConversionBean = currencyExchangeServiceProxy.getExchange(from, to);		
		if(currencyConversionBean != null) {
			currencyConversionBean.setQuantity(quantity);
			BigDecimal conversionMultiple = currencyConversionBean.getConversionMultiple();
			currencyConversionBean.setTotalCalculatedAmount(conversionMultiple.multiply(quantity));
		}
		return currencyConversionBean;
	}
	
}
