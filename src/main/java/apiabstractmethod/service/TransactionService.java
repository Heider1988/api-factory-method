package apiabstractmethod.service;

import apiabstractmethod.model.entity.AccountEntity;
import java.math.BigDecimal;

public interface TransactionService {

    AccountEntity processTransaction(String transactionType, String accountNumber, BigDecimal amount);

    String[] getAvailableTransactionTypes();
}