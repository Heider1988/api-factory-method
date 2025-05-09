package apiabstractmethod.service.impl;

import apiabstractmethod.model.entity.AccountEntity;
import apiabstractmethod.repository.AccountRepository;
import apiabstractmethod.service.Transaction;
import apiabstractmethod.service.TransactionFactory;
import apiabstractmethod.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of the TransactionService interface.
 * This class demonstrates the use of the Abstract Method pattern by selecting
 * the appropriate factory based on the transaction type.
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    
    private final AccountRepository accountRepository;
    private final Map<String, TransactionFactory> transactionFactories;
    
    /**
     * Constructor that injects all available TransactionFactory beans.
     * This demonstrates dependency injection and the Open/Closed principle from SOLID.
     */
    public TransactionServiceImpl(AccountRepository accountRepository, List<TransactionFactory> factories) {
        this.accountRepository = accountRepository;
        // Map factories by their transaction type for easy lookup
        this.transactionFactories = factories.stream()
                .collect(Collectors.toMap(
                        TransactionFactory::getTransactionType,
                        Function.identity()
                ));
    }
    
    @Override
    @Transactional
    public AccountEntity processTransaction(String transactionType, String accountNumber, BigDecimal amount) {
        // Find the account
        AccountEntity account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountNumber));
        
        // Get the appropriate factory
        TransactionFactory factory = transactionFactories.get(transactionType);
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported transaction type: " + transactionType);
        }
        
        // Create and execute the transaction
        Transaction transaction = factory.createTransaction(amount, account);
        AccountEntity updatedAccount = transaction.execute();
        
        // Save the updated account
        return accountRepository.save(updatedAccount);
    }
    
    @Override
    public String[] getAvailableTransactionTypes() {
        return transactionFactories.keySet().toArray(new String[0]);
    }
}