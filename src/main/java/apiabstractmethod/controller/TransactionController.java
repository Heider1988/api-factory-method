package apiabstractmethod.controller;

import apiabstractmethod.model.entity.AccountEntity;
import apiabstractmethod.request.TransactionRequest;
import apiabstractmethod.response.TransactionResponse;
import apiabstractmethod.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for transaction operations.
 */
@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Transaction API", description = "Endpoints for processing financial transactions")
public class TransactionController {
    
    private final TransactionService transactionService;
    
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    @PostMapping
    @Operation(summary = "Process a transaction", description = "Processes a financial transaction (deposit, withdrawal) on an account")
    public ResponseEntity<TransactionResponse> processTransaction(@Valid @RequestBody TransactionRequest request) {
        try {
            AccountEntity updatedAccount = transactionService.processTransaction(
                    request.getTransactionType(),
                    request.getAccountNumber(),
                    request.getAmount()
            );
            
            TransactionResponse response = new TransactionResponse(
                    request.getTransactionType(),
                    request.getAccountNumber(),
                    request.getAmount(),
                    updatedAccount.getBalance(),
                    "SUCCESS",
                    "Transaction processed successfully"
            );
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            TransactionResponse response = new TransactionResponse(
                    request.getTransactionType(),
                    request.getAccountNumber(),
                    request.getAmount(),
                    null,
                    "FAILED",
                    e.getMessage()
            );
            
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    @GetMapping("/types")
    @Operation(summary = "Get available transaction types", description = "Retrieves a list of all available transaction types")
    public ResponseEntity<String[]> getAvailableTransactionTypes() {
        return ResponseEntity.ok(transactionService.getAvailableTransactionTypes());
    }
}