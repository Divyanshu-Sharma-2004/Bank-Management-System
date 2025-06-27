# 🏦 Bank Management System

A GUI-based Bank Management System developed in Java using Swing and JDBC. This project simulates core banking functionalities such as user registration, login, deposit, withdrawal, balance enquiry, and PIN change.

---

## 📌 Features

- 📝 **Multi-step Signup Process**
  - Page 1: Personal details
  - Page 2: Additional info (PAN, Aadhaar, Income, etc.)
  - Page 3: Account type selection and card/PIN generation
- 🔐 **Login System**
  - Secure login with card number and PIN
- 💳 **Transaction Operations**
  - Deposit
  - Withdrawal
  - Fast Cash
  - Balance Enquiry
  - Mini Statement
  - PIN Change
- 📄 **Mini Statement View**
  - Displays previous transaction records

---

## 🛠️ Technologies Used

- 💻 **Java SE** – Core logic, GUI
- 🖼 **Swing** – User Interface components
- 🗃 **JDBC** – Database connectivity
- 🧮 **MySQL** – Data storage and retrieval
- 📆 **JCalendar** – Date picker (`JDateChooser`)
- 🎨 **AWT** – Layout and event handling

---

## 🗃️ Database Structure

**MySQL Tables:**
- `signup` – User personal details
- `signuptwo` – Additional details
- `signupthree` – Account type, card, PIN
- `login` – Stores login credentials
- `bank` – Transaction records
