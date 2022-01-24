package com.example.demo.client.model.response.transaction;

import java.util.List;

public record TransactionResponse(List<TransactionValueResponse> list) {
}