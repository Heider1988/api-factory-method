package apiabstractmethod.controller;

import apiabstractmethod.mapper.AccountMapper;
import apiabstractmethod.model.entity.AccountEntity;
import apiabstractmethod.repository.AccountRepository;
import apiabstractmethod.request.AccountRequest;
import apiabstractmethod.response.AccountResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account API", description = "Endpoints for managing bank accounts")
public class AccountController {
    
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    
    public AccountController(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }
    
    @PostMapping
    @Operation(summary = "Create a new account", description = "Creates a new bank account with the provided details")
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest request) {
        AccountEntity entity = accountMapper.toEntity(request);
        AccountEntity savedEntity = accountRepository.save(entity);
        return new ResponseEntity<>(accountMapper.toResponse(savedEntity), HttpStatus.CREATED);
    }
    
    @GetMapping
    @Operation(summary = "Get all accounts", description = "Retrieves a list of all bank accounts")
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<AccountResponse> accounts = accountRepository.findAll().stream()
                .map(accountMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accounts);
    }
    
    @GetMapping("/{accountNumber}")
    @Operation(summary = "Get account by number", description = "Retrieves a bank account by its account number")
    public ResponseEntity<AccountResponse> getAccountByNumber(@PathVariable String accountNumber) {
        Optional<AccountEntity> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        return accountOpt
                .map(account -> ResponseEntity.ok(accountMapper.toResponse(account)))
                .orElse(ResponseEntity.notFound().build());
    }
}