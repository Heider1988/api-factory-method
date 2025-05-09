package apiabstractmethod.service.impl;

import apiabstractmethod.model.entity.AccountEntity;
import apiabstractmethod.repository.AccountRepository;
import apiabstractmethod.request.TransactionRequest;
import apiabstractmethod.response.TransactionResponse;
import apiabstractmethod.service.Transaction;
import apiabstractmethod.service.TransactionFactory;
import apiabstractmethod.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public TransactionResponse processTransaction(TransactionRequest request) {

        AccountEntity account = accountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new IllegalArgumentException("Account not found: " + request.getAccountNumber()));

        TransactionFactory factory = transactionFactories.get(request.getTransactionType().toUpperCase());
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported transaction type: " + request.getTransactionType());
        }

        Transaction transaction = factory.createTransaction(request.getAmount(), account);
        AccountEntity updatedAccount = transaction.execute();

        accountRepository.save(updatedAccount);

        return new TransactionResponse(
                updatedAccount.getAccountType(),
                updatedAccount.getAccountNumber(),
                request.getAmount(),
                updatedAccount.getBalance(),
                "SUCCESS",
                "Transaction processed successfully") {

        };
    }


    @Override
    public String[] getAvailableTransactionTypes() {
        return transactionFactories.keySet().toArray(new String[0]);
    }
}