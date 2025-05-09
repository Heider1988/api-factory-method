package apiabstractmethod.service;

import apiabstractmethod.model.entity.AccountEntity;
import java.math.BigDecimal;

/**
 * Service interface for processing financial transactions.
 */
public interface TransactionService {
    
    /**
     * Process a transaction on an account.
     * 
     * @param transactionType The type of transaction to process (e.g., "DEPOSIT", "WITHDRAWAL")
     * @param accountNumber The account number on which to perform the transaction
     * @param amount The amount involved in the transaction
     * @return The updated account after the transaction
     */
    AccountEntity processTransaction(String transactionType, String accountNumber, BigDecimal amount);
    
    /**
     * Get all available transaction types.
     * 
     * @return An array of available transaction types
     */
    String[] getAvailableTransactionTypes();
}