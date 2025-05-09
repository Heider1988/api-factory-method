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

@Service
public class TransactionServiceImpl implements TransactionService {
    
    private final AccountRepository accountRepository;
    private final Map<String, TransactionFactory> transactionFactories;

    public TransactionServiceImpl(AccountRepository accountRepository, List<TransactionFactory> factories) {
        this.accountRepository = accountRepository;
        this.transactionFactories = factories.stream()
                .collect(Collectors.toMap(
                        TransactionFactory::getTransactionType,
                        Function.identity()
                ));
    }
    
    @Override
    @Transactional
    public AccountEntity processTransaction(String transactionType, String accountNumber, BigDecimal amount) {
        AccountEntity account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + accountNumber));

        TransactionFactory factory = transactionFactories.get(transactionType);
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported transaction type: " + transactionType);
        }

        Transaction transaction = factory.createTransaction(amount, account);
        AccountEntity updatedAccount = transaction.execute();

        return accountRepository.save(updatedAccount);
    }
    
    @Override
    public String[] getAvailableTransactionTypes() {
        return transactionFactories.keySet().toArray(new String[0]);
    }
}