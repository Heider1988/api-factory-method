package apiabstractmethod.service;

import apiabstractmethod.model.entity.AccountEntity;
import java.math.BigDecimal;

public interface TransactionFactory {

    Transaction createTransaction(BigDecimal amount, AccountEntity account);

    String getTransactionType();
}