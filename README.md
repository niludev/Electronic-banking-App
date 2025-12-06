# Electronic Banking System  
### Java • JDBC • PostgreSQL • Multi-Tier Architecture

A fully functional **console-based electronic banking system** built with **Java**, **JDBC**, and **PostgreSQL**, following a clean **N-Tier architecture** (View → Service → Repository → Database).  
The application supports **user authentication**, **bank card management**, **multiple transfer modes**, **batch transfers**, and a complete **transaction audit log** (successful & failed).

---

## What I Learned:
- Correct use of JDBC, PreparedStatement, ResultSet
- How to design a clean multi-layer architecture
- Writing reusable business logic
- Designing and enforcing banking fee algorithms
- Handling batch transactions with shared batch_id
- Robust error handling & validation
- Separation of concerns
- Creating maintainable, scalable Java applications

---

## Project Overview

This project simulates a simplified real-world electronic banking platform.  
Users can register, log in, create cards, and perform different types of money transfers:

-  **Card-to-Card transfer**  
-  **PAYA Single transfer**  
-  **PAYA Batch transfer**  
-  **SATNA transfer**

Every action—successful or failed—is recorded in the **transactions** table.

---

## Project Goals

- Practicing **JDBC (Java Database Connectivity)**  
- Implementing a **multi-layer (Tier-N)** architecture  
- Understanding **business rules** and **banking transfer logic**  
- Managing **SQL tables**, foreign keys, and relations  
- Logging real-world banking transaction details  
- Writing clean, maintainable, testable Java code  

---

## Features

### User Management
- User registration  
- Prevent duplicate usernames  
- Login with username + password  
- Logout feature  
- Logged-in session tracking  
- Relation between user → cards

---

### Card Management
| Feature | Description |
|--------|-------------|
| Register card | Assign card to logged-in user |
| Delete card | Remove card by number |
| Show card | Search by card number |
| Show by bank | List cards for a specific bank |
| Show all cards | Admin-like listing |

---

## Financial Operations

### Card-to-Card Transfer
Rules:
- Max amount → **15,000,000**
- Fee rules:
  - Same-bank → **0**
  - Different banks:
    - Amount ≤ 10M → fee = **720**
    - Amount > 10M → fee = **1000 + 100 per extra million**

Failed cases are logged with reason.

---

### PAYA Single Transfer
- Max allowed → **50,000,000**
- Fee → **0.001 × amount**
- Logs failures (insufficient balance, invalid amount)

---

### PAYA Batch Transfer
- Multiple transactions (items) in a single operation  
- Generates a unique `batch_id`  
- Fee rules:
  - Up to 10 items → **12,000**
  - Additional items → **+1,200 each**
- Logs every successful or failed transaction in the batch

---

### SATNA Transfer
- Min amount → **50,000,000**
- Max amount → **200,000,000**
- Fee → **0.002 × amount**
- Logs every transaction

---

## Transaction Logging

Every transaction (successful or failed) is inserted into the `transactions` table.

Fields logged include:
- from_card_id  
- to_card_id  
- amount  
- fee  
- type  
- status  
- description  
- batch_id  
- created_at  

This ensures a complete audit history.

---

## Architecture (N-Tier)

- view/                → Console user interface
- service/             → Business logic layer
- repository/          → JDBC database layer
- models/              → Domain models + enums
- util/                → ApplicationContext & DB configuration


## How to Run the Project

- Database: docker compose up -d --build
- app: javac Main.java



