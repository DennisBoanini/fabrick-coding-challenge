package com.example.demo.model.transaction;

import java.util.List;

public record Transaction(List<TransactionValue> transactions) {
}