package com.currencyexchangeservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.currencyexchangeservice.entities.ExchangeValue;

@Repository
public interface CurrencyExchangeJPARepository extends JpaRepository<ExchangeValue, Integer>{
	
	ExchangeValue findByFromAndTo(String from, String to);

}
