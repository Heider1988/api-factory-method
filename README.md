# Banking API with Abstract Method Pattern

This project demonstrates the implementation of a simple banking API using the Abstract Method design pattern. The API provides endpoints for managing bank accounts and processing financial transactions such as deposits and withdrawals.

## Project Overview

This Spring Boot application implements a RESTful API for a basic banking system. It allows users to:

- Create and manage bank accounts
- Perform financial transactions (deposits and withdrawals)
- View transaction history and account balances

The project showcases how to use the Abstract Method design pattern to create a flexible and extensible system for handling different types of financial transactions.

## Abstract Method Pattern

The Abstract Method pattern (also known as Abstract Factory) is a creational design pattern that provides an interface for creating families of related or dependent objects without specifying their concrete classes. This pattern is particularly useful when:

- A system needs to be independent of how its products are created, composed, and represented
- A system should be configured with one of multiple families of products
- A family of related product objects is designed to be used together

In this project, the pattern is used to create different types of financial transactions (deposit, withdrawal) without the client code needing to know the specific implementation details of each transaction type.

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

The following sequence diagram illustrates how the Abstract Method pattern is implemented in this API:

```
┌─────────┐          ┌───────────────────┐          ┌─────────────────────┐          ┌─────────────────────────┐          ┌─────────────────────────┐
│ Client  │          │TransactionController│          │TransactionServiceImpl│          │TransactionFactory (Map) │          │Concrete Factory         │
└────┬────┘          └──────────┬────────┘          └──────────┬──────────┘          └───────────┬─────────────┘          └───────────┬─────────────┘
     │                          │                              │                                  │                                    │
     │ POST /api/transactions   │                              │                                  │                                    │
     │─────────────────────────>│                              │                                  │                                    │
     │                          │                              │                                  │                                    │
     │                          │ processTransaction()         │                                  │                                    │
     │                          │─────────────────────────────>│                                  │                                    │
     │                          │                              │ Find account by number           │                                    │
     │                          │                              │ ────────────────────────         │                                    │
     │                          │                              │                                  │                                    │
     │                          │                              │ Get factory by transaction type  │                                    │
     │                          │                              │─────────────────────────────────>│                                    │
     │                          │                              │                                  │                                    │
     │                          │                              │                                  │ Return specific factory            │
     │                          │                              │<─────────────────────────────────│                                    │
     │                          │                              │                                  │                                    │
     │                          │                              │ createTransaction()              │                                    │
     │                          │                              │─────────────────────────────────────────────────────────────────────>│
     │                          │                              │                                  │                                    │
     │                          │                              │                                  │                                    │
     │                          │                              │                                  │                                    │
     │                          │                              │<─────────────────────────────────────────────────────────────────────│
     │                          │                              │                                  │                                    │
     │                          │                              │                                  │                                    │
┌─────────┐          ┌───────────────────┐          ┌─────────────────────┐          ┌─────────────────────────┐          ┌─────────────────────────┐
│ Client  │          │TransactionController│          │TransactionServiceImpl│          │TransactionFactory (Map) │          │Concrete Transaction     │
└────┬────┘          └──────────┬────────┘          └──────────┬──────────┘          └───────────┬─────────────┘          └───────────┬─────────────┘
     │                          │                              │                                  │                                    │
     │                          │                              │ execute()                        │                                    │
     │                          │                              │─────────────────────────────────────────────────────────────────────>│
     │                          │                              │                                  │                                    │
     │                          │                              │                                  │                                    │ Perform transaction
     │                          │                              │                                  │                                    │ logic (deposit or
     │                          │                              │                                  │                                    │ withdrawal)
     │                          │                              │                                  │                                    │ ─────────────────
     │                          │                              │                                  │                                    │
     │                          │                              │ Return updated account           │                                    │
     │                          │                              │<─────────────────────────────────────────────────────────────────────│
     │                          │                              │                                  │                                    │
     │                          │                              │ Save updated account             │                                    │
     │                          │                              │ ────────────────────────         │                                    │
     │                          │                              │                                  │                                    │
     │                          │ Return transaction response  │                                  │                                    │
     │                          │<─────────────────────────────│                                  │                                    │
     │                          │                              │                                  │                                    │
     │ Transaction Response     │                              │                                  │                                    │
     │<─────────────────────────│                              │                                  │                                    │
     │                          │                              │                                  │                                    │
```

This diagram shows how:
1. The client sends a transaction request to the controller
2. The controller delegates to the service layer
3. The service finds the appropriate factory from its map of factories
4. The factory creates a concrete transaction object
5. The transaction is executed, updating the account
6. The updated account is saved and a response is returned to the client

This implementation demonstrates the Abstract Method pattern by using factories to create different types of transactions without the client code needing to know the concrete classes.

## Installation and Setup

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Git

### Steps to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Heider1988/api-factory-method.git
   cd api-factory-method
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. The API will be available at `http://localhost:8080`
5. Access the Swagger UI documentation at `http://localhost:8080/swagger-ui.html`

## Usage Examples

### Creating a New Account

```bash
curl -X POST http://localhost:8080/api/accounts \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "initialBalance": 1000.00
  }'
```

Response:
```json
{
  "accountNumber": "ACC123456",
  "name": "John Doe",
  "balance": 1000.00,
  "createdAt": "2023-06-15T10:30:45"
}
```

### Making a Deposit

```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{
    "accountNumber": "ACC123456",
    "transactionType": "DEPOSIT",
    "amount": 500.00
  }'
```

Response:
```json
{
  "transactionType": "DEPOSIT",
  "accountNumber": "ACC123456",
  "amount": 500.00,
  "newBalance": 1500.00,
  "status": "SUCCESS",
  "message": "Transaction processed successfully"
}
```

### Making a Withdrawal

```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -d '{
    "accountNumber": "ACC123456",
    "transactionType": "WITHDRAWAL",
    "amount": 200.00
  }'
```

Response:
```json
{
  "transactionType": "WITHDRAWAL",
  "accountNumber": "ACC123456",
  "amount": 200.00,
  "newBalance": 1300.00,
  "status": "SUCCESS",
  "message": "Transaction processed successfully"
}
```

### Getting Available Transaction Types

```bash
curl -X GET http://localhost:8080/api/transactions/types
```

Response:
```json
["DEPOSIT", "WITHDRAWAL"]
```

## API Documentation

### Account API

| Endpoint | Method | Description | Request Body | Response |
|----------|--------|-------------|--------------|----------|
| `/api/accounts` | POST | Create a new account | `AccountRequest` | `AccountResponse` |
| `/api/accounts` | GET | Get all accounts | - | List of `AccountResponse` |
| `/api/accounts/{accountNumber}` | GET | Get account by number | - | `AccountResponse` |

### Transaction API

| Endpoint | Method | Description | Request Body | Response |
|----------|--------|-------------|--------------|----------|
| `/api/transactions` | POST | Process a transaction | `TransactionRequest` | `TransactionResponse` |
| `/api/transactions/types` | GET | Get available transaction types | - | Array of strings |


### 4. That's it!

The system will automatically recognize the new transaction type because:

1. Spring will detect and register the new factory as a bean
2. The `TransactionServiceImpl` collects all `TransactionFactory` implementations during initialization
3. The new transaction type will appear in the available transaction types endpoint

This demonstrates the Open/Closed Principle: the system is open for extension but closed for modification.
