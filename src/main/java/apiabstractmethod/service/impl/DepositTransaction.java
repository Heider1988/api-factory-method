package apiabstractmethod.service.impl;

import apiabstractmethod.model.entity.AccountEntity;
import apiabstractmethod.service.Transaction;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class DepositTransaction implements Transaction {
    
    private final BigDecimal amount;
    private final AccountEntity account;
    private final String type = "DEPOSIT";
    
    public DepositTransaction(BigDecimal amount, AccountEntity account) {
        this.amount = amount;
        this.account = account;
    }
    
    @Override
    public AccountEntity execute() {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        
        return account;
    }
    
    @Override
    public String getType() {
        return type;
    }
}