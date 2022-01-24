package com.example.demo.client.impl;

import com.example.demo.client.BankAccountClient;
import com.example.demo.client.exception.ServerException;
import com.example.demo.client.mapper.BankAccountClientMapper;
import com.example.demo.client.model.response.balance.BalanceResponse;
import com.example.demo.client.model.response.bank_transfer.BankTransferResponse;
import com.example.demo.client.model.response.server.ServerResponse;
import com.example.demo.client.model.response.transaction.TransactionResponse;
import com.example.demo.model.Balance;
import com.example.demo.model.transaction.Transaction;
import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static com.example.demo.client.model.response.enumeration.EServerResponseStatus.KO;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class BankAccountClientImpl implements BankAccountClient {

	private final static Logger logger = LoggerFactory.getLogger(BankAccountClientImpl.class);
	private final static String BASE_URL = "/banking/v4.0/accounts/";
	private final WebClient webClient;
	private final BankAccountClientMapper mapper;

	public BankAccountClientImpl(final WebClient webClient, final BankAccountClientMapper mapper) {
		this.webClient = webClient;
		this.mapper = mapper;
	}

	@Override
	public Balance getAccountBalance(final long accountId) {
		logger.debug("Start retrieving balance for given account {}", accountId);
		final ServerResponse<BalanceResponse> response = this.webClient
				.get()
				.uri(BASE_URL + "/{accountId}/balance", accountId)
				.exchangeToMono(r -> r.bodyToMono(new ParameterizedTypeReference<ServerResponse<BalanceResponse>>() {
				})).block();

		if (KO.equals(response.getStatus())) {
			logger.error("Error retrieving balance for given account {}", accountId);
			throw new ServerException(response.getErrors());
		}

		logger.debug("Finish retrieving balance for given account {}", accountId);
		return this.mapper.toModel(response.getPayload());
	}

	@Override
	public Transaction getAccountTransactions(final long accountId, final LocalDate from, final LocalDate to) {
		logger.debug("Start retrieving transactions for given account {}", accountId);
		final UriComponents uriComponents = UriComponentsBuilder
				.fromPath(BASE_URL + accountId + "/transactions")
				.queryParam("fromAccountingDate", from)
				.queryParam("toAccountingDate", to)
				.build();

		logger.debug("Created URI for retrieving transaction for account: {}", uriComponents.toUriString());

		final ServerResponse<TransactionResponse> response = this.webClient
				.get()
				.uri(uriComponents.toUriString())
				.exchangeToMono(clientResponse -> clientResponse.bodyToMono(new ParameterizedTypeReference<ServerResponse<TransactionResponse>>() {
				})).block();

		if (KO.equals(response.getStatus())) {
			logger.debug("Error retrieving transactions for given account {}", accountId);
			throw new ServerException(response.getErrors());
		}

		logger.debug("Finish retrieving transactions for given account {}", accountId);
		return this.mapper.toModel(response.getPayload());

	}

	@Override
	public BankTransferResponse makeMoneyTransfer(final long accountId, final String timeZone, final MoneyTransfer transfer) {
		final ObjectMapper objectMapper = new ObjectMapper();
		try {
			logger.debug("Start making money transfer for given account {}. \nTransfer object is {}", accountId, objectMapper.writeValueAsString(transfer));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		final UriComponents uriComponents = UriComponentsBuilder
				.fromPath(BASE_URL)
				.path(Long.valueOf(accountId).toString())
				.path("/payments/money-transfers")
				.build();

		logger.debug("URI for money transfer request is {}", uriComponents.toUriString());

		final ServerResponse<BankTransferResponse> response = this.webClient
				.post()
				.uri(uriComponents.toUriString())
				.header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
				.header("X-Time-Zone", timeZone)
				.body(Mono.just(transfer), MoneyTransfer.class)
				.exchangeToMono(clientResponse -> clientResponse.bodyToMono(new ParameterizedTypeReference<ServerResponse<BankTransferResponse>>() {
				})).block();

		if (KO.equals(response.getStatus())) {
			logger.error("Error during money transfer for account {}", accountId);
			throw new ServerException(response.getErrors());
		}

		logger.debug("Finish making money transfer");
		return response.getPayload();
	}
}
