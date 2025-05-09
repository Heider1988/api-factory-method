package apiabstractmethod.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO for transaction response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    
    private String transactionType;
    private String accountNumber;
    private BigDecimal amount;
    private BigDecimal newBalance;
    private String status;
    private String message;
}