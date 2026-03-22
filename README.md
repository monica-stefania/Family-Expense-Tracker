# Family Expense Tracker 

A RESTful application for managing family members expenses.
Each family member has their own accound and can record expenses in different categories.

## Arhitecture

- Auth -> handles user registration and login
- Expense -> manages expenses
- Crypto -> encrypts sensitive data

## Services

### Auth Service (port 8081)
- POST /auth/register
- POST /auth/login

### Crypto Service (port 8082)
- POST /crypto/encrypt

### Expense Service (port 8082)
- POST /expenses           -> Add expense
- GET /expenses            -> Get expense
- DELETE /expenses/{id}    -> Delete expense
- PUT /expenses/{id}       -> Update expense
