package apiabstractmethod.mapper;

import apiabstractmethod.model.entity.AccountEntity;
import apiabstractmethod.request.AccountRequest;
import apiabstractmethod.response.AccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    
    /**
     * Convert an AccountRequest to an AccountEntity.
     * 
     * @param request The account request DTO
     * @return A new AccountEntity
     */
    public AccountEntity toEntity(AccountRequest request) {
        return new AccountEntity(
                null,
                request.getAccountNumber(),
                request.getAccountType(),
                request.getInitialBalance(),
                request.getOwnerName()
        );
    }
    
    /**
     * Convert an AccountEntity to an AccountResponse.
     * 
     * @param entity The account entity
     * @return A new AccountResponse
     */
    public AccountResponse toResponse(AccountEntity entity) {
        return new AccountResponse(
                entity.getId(),
                entity.getAccountNumber(),
                entity.getAccountType(),
                entity.getBalance(),
                entity.getOwnerName()
        );
    }
}