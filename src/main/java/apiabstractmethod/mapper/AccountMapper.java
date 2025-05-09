package apiabstractmethod.mapper;

import apiabstractmethod.model.entity.AccountEntity;
import apiabstractmethod.request.AccountRequest;
import apiabstractmethod.response.AccountResponse;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountEntity toEntity(AccountRequest request) {
        return new AccountEntity(
                null,
                request.getAccountNumber(),
                request.getAccountType(),
                request.getInitialBalance(),
                request.getOwnerName()
        );
    }

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