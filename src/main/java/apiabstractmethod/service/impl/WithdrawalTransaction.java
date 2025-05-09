package apiabstractmethod.service.impl;

import apiabstractmethod.model.entity.AccountEntity;
import apiabstractmethod.service.Transaction;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class WithdrawalTransaction implements Transaction {
    
    private final BigDecimal amount;
    private final AccountEntity account;
    private static final String TYPE = "WITHDRAWAL";
    
    public WithdrawalTransaction(BigDecimal amount, AccountEntity account) {
        this.amount = amount;
        this.account = account;
    }
    
    @Override
    public AccountEntity execute() {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        if (account.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal");
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        
        return account;
    }
    
    @Override
    public String getType() {
        return TYPE;
    }
}