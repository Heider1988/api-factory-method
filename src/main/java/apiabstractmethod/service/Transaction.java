package apiabstractmethod.service;

import apiabstractmethod.model.entity.AccountEntity;
import java.math.BigDecimal;

public interface Transaction {
    
    /**
     * Executes the financial transaction.
     * 
     * @return The updated account after the transaction
     */
    AccountEntity execute();
    
    /**
     * Returns the amount involved in the transaction.
     * 
     * @return The transaction amount
     */
    BigDecimal getAmount();
    
    /**
     * Returns the account on which the transaction is performed.
     * 
     * @return The account entity
     */
    AccountEntity getAccount();
    
    /**
     * Returns the type of transaction.
     * 
     * @return A string representing the transaction type
     */
    String getType();
}