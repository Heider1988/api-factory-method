# Banking API with Abstract Method Pattern

This project demonstrates the implementation of a simple banking API using the Abstract Method design pattern. The API provides endpoints for managing bank accounts and processing financial transactions such as deposits and withdrawals.

## Abstract Method Pattern

The Abstract Method pattern is a creational design pattern that provides an interface for creating families of related or dependent objects without specifying their concrete classes. In this project, the pattern is used to create different types of financial transactions.

### Key Components

1. **Abstract Factory Interface**: `TransactionFactory` - Declares methods for creating transaction objects
2. **Concrete Factories**: `DepositTransactionFactory`, `WithdrawalTransactionFactory` - Implement the factory interface to create specific transaction types
3. **Abstract Product Interface**: `Transaction` - Declares the operations that all transaction types must implement
4. **Concrete Products**: `DepositTransaction`, `WithdrawalTransaction` - Implement the transaction interface for specific transaction types

## SOLID Principles

This project follows the SOLID principles:

- **Single Responsibility Principle**: Each class has a single responsibility (e.g., `AccountEntity` represents an account, `TransactionService` processes transactions)
- **Open/Closed Principle**: The system is open for extension (new transaction types can be added) but closed for modification
- **Liskov Substitution Principle**: Any concrete `Transaction` can be used where the `Transaction` interface is expected
- **Interface Segregation Principle**: Clients only depend on the interfaces they use
- **Dependency Inversion Principle**: High-level modules depend on abstractions, not concrete implementations

## Project Structure

```
├── config             # Configurations (Swagger)
├── controller         # REST Controllers (endpoints)
├── request            # Request DTOs
├── response           # Response DTOs
├── exception          # Exception handling
├── mapper             # Entity-DTO mappers
├── model.entity       # JPA Entities
├── repository         # Spring Data JPA Repositories
├── service            # Service interfaces
└── service.impl       # Service implementations
```

## API Endpoints

### Account API

- `POST /api/accounts` - Create a new account
- `GET /api/accounts` - Get all accounts
- `GET /api/accounts/{accountNumber}` - Get account by number

### Transaction API

- `POST /api/transactions` - Process a transaction
- `GET /api/transactions/types` - Get available transaction types

## Sequence Diagram

```
┌─────────┐          ┌───────────────────┐          ┌─────────────────────┐          ┌─────────────────┐          ┌────────────────┐
│ Client  │          │TransactionController│          │TransactionService   │          │TransactionFactory│          │Transaction     │
└────┬────┘          └──────────┬────────┘          └──────────┬──────────┘          └────────┬────────┘          └────────┬───────┘
     │                          │                              │                              │                            │
     │ POST /api/transactions   │                              │                              │                            │
     │─────────────────────────>│                              │                              │                            │
     │                          │                              │                              │                            │
     │                          │ processTransaction()         │                              │                            │
     │                          │─────────────────────────────>│                              │                            │
     │                          │                              │                              │                            │
     │                          │                              │ get factory by type          │                            │
     │                          │                              │─────────────────────────────>│                            │
     │                          │                              │                              │                            │
     │                          │                              │                              │ createTransaction()        │
     │                          │                              │                              │───────────────────────────>│
     │                          │                              │                              │                            │
     │                          │                              │                              │<───────────────────────────│
     │                          │                              │                              │                            │
     │                          │                              │ execute transaction          │                            │
     │                          │                              │───────────────────────────────────────────────────────────>│
     │                          │                              │                              │                            │
     │                          │                              │<───────────────────────────────────────────────────────────│
     │                          │                              │                              │                            │
     │                          │<─────────────────────────────│                              │                            │
     │                          │                              │                              │                            │
     │ Transaction Response     │                              │                              │                            │
     │<─────────────────────────│                              │                              │                            │
     │                          │                              │                              │                            │
```

## How to Run

1. Clone the repository
2. Run `mvn spring-boot:run`
3. Access the Swagger UI at `http://localhost:8080/swagger-ui.html`

## Adding New Transaction Types

To add a new transaction type:

1. Create a new concrete transaction class that implements the `Transaction` interface
2. Create a new concrete factory class that implements the `TransactionFactory` interface
3. Register the new factory as a Spring bean

The system will automatically recognize the new transaction type without modifying existing code, demonstrating the Open/Closed Principle.