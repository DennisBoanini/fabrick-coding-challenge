package com.example.demo.controller;

import com.example.demo.client.model.response.bank_transfer.BankTransferResponse;
import com.example.demo.client.model.response.server.ServerErrorResponse;
import com.example.demo.dto.BalanceDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.dto.bank_transfer.MoneyTransferDTO;
import com.example.demo.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/demo-api/account/{accountId}")
public class AccountRestController {

	private final AccountService accountService;

	public AccountRestController(final AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/balance")
	@Operation(summary = "Return the actual account balance")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "The actual balance for the account is retrieved",
					content = {
							@Content(
									mediaType = APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = BalanceDTO.class)
							)
					}
			),
			@ApiResponse(
					responseCode = "400",
					description = "",
					content = {
							@Content(
									mediaType = APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ServerErrorResponse.class)
							)
					}
			)
	})
	public ResponseEntity<BalanceDTO> getBalance(@Parameter(description = "The id of the account", example = "1234") @PathVariable final long accountId) {
		return ResponseEntity
				.ok()
				.body(this.accountService.getAccountBalance(accountId));
	}

	@GetMapping("/transactions")
	@Operation(summary = "Get all transactions of the given account")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "All transactions for the account are retrieved",
					content = {
							@Content(
									mediaType = APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = TransactionDTO.class)
							)
					}
			),
			@ApiResponse(
					responseCode = "400",
					description = "",
					content = {
							@Content(
									mediaType = APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ServerErrorResponse.class)
							)
					}
			)
	})
	public ResponseEntity<TransactionDTO> getTransactions(
			@Parameter(description = "The id of the account", example = "1234") @PathVariable final long accountId,
			@Parameter(description = "The starting date for the list of transactions", example = "2021-01-01") @RequestParam("from") final LocalDate from,
			@Parameter(description = "The ending date for the list of transactions", example = "2021-01-01") @RequestParam("to") final LocalDate to
	) {
		return ResponseEntity
				.ok()
				.body(this.accountService.getAccountTransactions(accountId, from, to));
	}

	@PostMapping(value = "/money-transfer", consumes = "application/json")
	@Operation(summary = "Make a money transfer")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "All transactions for the account are retrieved",
					content = {
							@Content(
									mediaType = APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = BankTransferResponse.class)
							)
					}
			),
			@ApiResponse(
					responseCode = "400",
					description = "",
					content = {
							@Content(
									mediaType = APPLICATION_JSON_VALUE,
									schema = @Schema(implementation = ServerErrorResponse.class)
							)
					}
			)
	})
	public ResponseEntity<BankTransferResponse> moneyTransfer(
			@Parameter(description = "The id of the account", example = "1234") @PathVariable final long accountId,
			@Parameter(description = "The time zone used to provide the request date fields", example = "Europe/Rome") @RequestHeader("X-Time-Zone") String timeZone,
			@Parameter(description = "The data to make a money transfer", required = true, schema = @Schema(implementation = MoneyTransferDTO.class)) @RequestBody MoneyTransferDTO moneyTransferDTO
	) {
		return ResponseEntity
				.ok()
				.body(this.accountService.makeMoneyTransfer(accountId, timeZone, moneyTransferDTO));
	}
}
