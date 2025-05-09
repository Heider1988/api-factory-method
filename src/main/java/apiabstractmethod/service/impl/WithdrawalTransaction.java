package apiabstractmethod.service.impl;

import apiabstractmethod.model.entity.AccountEntity;
import apiabstractmethod.service.Transaction;
import lombok.Getter;
import java.math.BigDecimal;

/**
 * Concrete Product implementation for withdrawal transactions.
 */
@Getter
public class WithdrawalTransaction implements Transaction {
    
    private final BigDecimal amount;
    private final AccountEntity account;
    private final String type = "WITHDRAWAL";
    
    public WithdrawalTransaction(BigDecimal amount, AccountEntity account) {
        this.amount = amount;
        this.account = account;
    }
    
    @Override
    public AccountEntity execute() {
        // Validate amount
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        
        // Check if sufficient funds are available
        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal");
        }
        
        // Perform withdrawal operation
        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        
        return account;
    }
    
    @Override
    public String getType() {
        return type;
    }
}