package apiabstractmethod.service.impl;

import apiabstractmethod.model.entity.AccountEntity;
import apiabstractmethod.service.Transaction;
import apiabstractmethod.service.TransactionFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

/**
 * Concrete Factory implementation for creating deposit transactions.
 */
@Service
public class DepositTransactionFactory implements TransactionFactory {
    
    @Override
    public Transaction createTransaction(BigDecimal amount, AccountEntity account) {
        return new DepositTransaction(amount, account);
    }
    
    @Override
    public String getTransactionType() {
        return "DEPOSIT";
    }
}