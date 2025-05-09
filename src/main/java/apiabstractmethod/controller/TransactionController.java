package apiabstractmethod.controller;

import apiabstractmethod.request.TransactionRequest;
import apiabstractmethod.response.TransactionResponse;
import apiabstractmethod.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        TransactionResponse response = transactionService.processTransaction(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/types")
    @Operation(summary = "Get available transaction types", description = "Retrieves a list of all available transaction types")
    public ResponseEntity<String[]> getAvailableTransactionTypes() {
        return ResponseEntity.ok(transactionService.getAvailableTransactionTypes());
    }
}