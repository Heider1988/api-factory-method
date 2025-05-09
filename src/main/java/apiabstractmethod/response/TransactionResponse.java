package apiabstractmethod.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object for transaction processing results")
public class TransactionResponse {

    @Schema(description = "Type of transaction that was processed", example = "deposit")
    private String transactionType;

    @Schema(description = "Account number involved in the transaction", example = "ACC123456")
    private String accountNumber;

    @Schema(description = "Amount of the transaction", example = "100.00")
    private BigDecimal amount;

    @Schema(description = "New balance after the transaction", example = "500.00")
    private BigDecimal newBalance;

    @Schema(description = "Status of the transaction", example = "SUCCESS", allowableValues = {"SUCCESS", "FAILED"})
    private String status;

    @Schema(description = "Detailed message about the transaction result", example = "Transaction processed successfully")
    private String message;
}
