package apiabstractmethod.repository;

import apiabstractmethod.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    
    /**
     * Find an account by its account number.
     * 
     * @param accountNumber The account number to search for
     * @return An Optional containing the account if found
     */
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}