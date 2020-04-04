package com.app.aforo255.withdrawal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.aforo255.withdrawal.domain.Transaction;
import com.app.aforo255.withdrawal.producer.WithdrawalEventProducer;
import com.app.aforo255.withdrawal.service.ITransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class WithdrawalEventController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WithdrawalEventController.class);
	@Autowired WithdrawalEventProducer withdrawalEventProducer;
	private @Autowired ITransactionService transactionService;
	
	@PostMapping("/v1/withdrawalEvent")
	public ResponseEntity<Transaction> postLibraryEvent(@RequestBody Transaction transactionEvent)
		throws JsonProcessingException {
		LOGGER.info("antes sendWithdrawalEvent_Approach3");
		Transaction transaction = this.transactionService.save(transactionEvent);
		this.withdrawalEventProducer.sendWithdrawalEvent_Approach3(transaction);
		LOGGER.info("despues sendWithdrawalEvent_Approach3");
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(transactionEvent);
	}
}
