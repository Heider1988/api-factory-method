package apiabstractmethod.service;

import apiabstractmethod.request.TransactionRequest;
import apiabstractmethod.response.TransactionResponse;

public interface TransactionService {

    TransactionResponse processTransaction(TransactionRequest request);

    String[] getAvailableTransactionTypes();
}