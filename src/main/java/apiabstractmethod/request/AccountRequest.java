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
@Schema(description = "Request object for creating a new bank account")
public class AccountRequest {

    @NotBlank(message = "Account number is required")
    @Schema(description = "Unique account number", example = "ACC123456", required = true)
    private String accountNumber;

    @NotBlank(message = "Account type is required")
    @Schema(description = "Type of account (checking, savings, etc.)", example = "savings", required = true)
    private String accountType;

    @NotNull(message = "Initial balance is required")
    @Positive(message = "Initial balance must be positive")
    @Schema(description = "Initial deposit amount for the account", example = "1000.00", required = true)
    private BigDecimal initialBalance;

    @NotBlank(message = "Owner name is required")
    @Schema(description = "Name of the account owner", example = "John Doe", required = true)
    private String ownerName;
}
