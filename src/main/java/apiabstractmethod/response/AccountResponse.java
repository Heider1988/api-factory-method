package apiabstractmethod.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response object containing bank account details")
public class AccountResponse {

    @Schema(description = "Unique identifier for the account in the database", example = "1")
    private Long id;

    @Schema(description = "Unique account number", example = "ACC123456")
    private String accountNumber;

    @Schema(description = "Type of account (checking, savings, etc.)", example = "savings")
    private String accountType;

    @Schema(description = "Current balance of the account", example = "1500.00")
    private BigDecimal balance;

    @Schema(description = "Name of the account owner", example = "John Doe")
    private String ownerName;
}
