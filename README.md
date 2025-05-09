# 🚀 BlockVest - Full Stack Cryptocurrency Trading Platform

BlockVest is a secure and scalable cryptocurrency trading platform built with modern full-stack technologies. It allows users to seamlessly buy/sell crypto, manage their portfolio, perform wallet transfers, and withdraw funds directly to their bank accounts — all with enterprise-grade security and a sleek UI.

---

## 🛠️ Tech Stack

### 🔙 Backend
- **Spring Boot**
- **MySQL**
- **Spring Security**
- **Java Mail Sender**

### 🌐 Frontend
- **React.js**
- **Tailwind CSS**
- **Redux**
- **Axios**
- **React-Router-Dom**
- **Shadcn UI**

### 💳 Payment Integration
- **Razorpay**
- **Stripe**

### 🔗 APIs
- **CoinGecko API** (for crypto market data)

---

## 📦 Features

### 🔐 Authentication & Security
- User Registration & Login
- Secure JWT-based Session Management
- Two-Factor Authentication (2FA)
- Forgot Password & Email-based Password Reset

### 💰 Crypto Trading
- Buy & Sell a wide range of cryptocurrencies
- Real-time price updates
- Transaction confirmation and logging

### 🧾 Wallet Functionality
- Wallet-to-Wallet Crypto Transfers
- Add Balance to Wallet via Razorpay/Stripe
- Withdraw Funds to Bank Account

### 📊 Portfolio Management
- View Owned Crypto Assets
- Track Portfolio Value and Transaction Performance

### 🧾 Transaction History
- View Wallet Activity
- View Withdrawal History
- View Buy/Sell Records

### 🔍 Coin Search
- Search and explore available cryptocurrencies with live price and metadata

---

## 🧩 Architecture Overview

- **Backend:** Follows a layered architecture (Controller → Service → Repository)
- **Frontend:** Component-based React structure with Redux for state management
- **Database:** MySQL relational database, normalized for secure and efficient storage
- **Security:** JWT + Spring Security + OTP (2FA) for maximum protection
- **Payments:** Integrated Razorpay and Stripe payment gateways for topping up wallets

---

## 🚀 Setup Instructions

### Prerequisites
- Java 17+
- Node.js & npm
- MySQL Server
- Maven
---

### 🛠️ Backend Setup

1. Open the `backend` folder in **IntelliJ IDEA**.
2. Go to the `src/main/resources/application.properties` file.
3. Update the following:
   -  **Database Credentials**: Set your MySQL `username`, `password`, and create a new database.
   -  **Email Configuration**: Use your **app password** for email-related services.
   -  **Payment Gateways**: Enter your **Razorpay API key** or **Stripe API key** as required.
4. Save the file.
5. Run the project
---

## 🔧 Sample Configuration (`application.properties`)

Below is a sample configuration for your backend Spring Boot application. Update the fields marked with `your ...` accordingly.

```properties
spring.application.name=block_vest
server.port=5454

spring.datasource.url=jdbc:mysql://localhost:3306/block_vest
spring.datasource.username=root
spring.datasource.password=your password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update

# Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your email
spring.mail.password=your app password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Stripe Payment Gateway
stripe.api.key=your key

# Razorpay Payment Gateway
razorpay.api.key=your razorpay key
razorpay.api.secret=your razorpay secret

# CoinGecko API (for market data)
coingecko.api.key=your coin gecko api key
