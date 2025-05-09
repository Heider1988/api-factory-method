package apiabstractmethod.service.impl;

import apiabstractmethod.model.entity.AccountEntity;
import apiabstractmethod.service.Transaction;
import apiabstractmethod.service.TransactionFactory;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class WithdrawalTransactionFactory implements TransactionFactory {
    
    @Override
    public Transaction createTransaction(BigDecimal amount, AccountEntity account) {
        return new WithdrawalTransaction(amount, account);
    }
    
    @Override
    public String getTransactionType() {
        return "WITHDRAWAL";
    }
}