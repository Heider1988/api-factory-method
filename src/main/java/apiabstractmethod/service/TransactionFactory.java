package apiabstractmethod.service;

import apiabstractmethod.model.entity.AccountEntity;
import java.math.BigDecimal;

/**
 * Abstract Factory interface for creating different types of financial transactions.
 * This is the core of the Abstract Method pattern.
 */
public interface TransactionFactory {
    
    /**
     * Creates a transaction object for processing financial operations.
     * 
     * @param amount The amount of money involved in the transaction
     * @param account The account on which the transaction will be performed
     * @return A Transaction object that can execute the financial operation
     */
    Transaction createTransaction(BigDecimal amount, AccountEntity account);
    
    /**
     * Returns the type of transaction this factory creates.
     * 
     * @return A string representing the transaction type
     */
    String getTransactionType();
}