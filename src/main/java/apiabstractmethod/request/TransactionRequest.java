package apiabstractmethod.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request object for processing financial transactions")
public class TransactionRequest {

    @NotBlank(message = "Transaction type is required")
    @Schema(description = "Type of transaction (deposit, withdrawal)", example = "deposit", required = true)
    private String transactionType;

    @NotBlank(message = "Account number is required")
    @Schema(description = "Account number for the transaction", example = "ACC123456", required = true)
    private String accountNumber;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @Schema(description = "Amount for the transaction", example = "100.00", required = true)
    private BigDecimal amount;
}
