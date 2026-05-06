# 📚 URLMS — University Resource & Loan Management System

A console-based Java application for managing library resources, member accounts, borrowing transactions, and fine tracking at a university level.

---

## Features

**Librarian Portal**
- Add and remove library resources
- Register new members
- View all resources and their availability
- View all transactions system-wide
- Search resources by keyword
- View outstanding fines report

**Member Portal**
- Search available resources
- Borrow resources with a custom loan period
- Return resources
- View active checkouts
- View personal fines

---

## Project Structure

```
URLMS/
├── URLMS.java              # Main application entry point & menu logic
├── User.java               # Abstract base class for all users
├── Librarian.java          # Librarian role (extends User)
├── Member.java             # Member role with borrow limits (extends User)
├── Resource.java           # Abstract base class for all resources
├── Book.java               # Book resource type (extends Resource)
├── Journal.java            # Journal resource type (extends Resource)
├── DigitalEquipment.java   # Equipment resource type (extends Resource)
└── Transaction.java        # Borrow/return transaction with fine calculation
```

---

## Class Hierarchy

```
User (abstract)
├── Librarian
└── Member

Resource (abstract)
├── Book
├── Journal
└── DigitalEquipment

Transaction
```

---

## Getting Started

### Prerequisites
- Java JDK 8 or higher

### Compile

```bash
javac *.java
```

### Run

```bash
java URLMS
```

---

## Default Credentials

> ⚠️ These credentials are hardcoded for demo purposes only. Do not use this pattern in production.

**Librarians**

| User ID | Name         | Password |
|---------|--------------|----------|
| L001    | Ahmed Ali    | lib123   |
| L002    | Fatima Khan  | lib456   |

**Members**

| User ID | Name              | Password | Type    |
|---------|-------------------|----------|---------|
| M001    | Hassan Raza       | pass123  | Student |
| M002    | Ayesha Malik      | pass456  | Student |
| M003    | Dr. Imran Sheikh  | pass789  | Faculty |

---

## Pre-loaded Resources

| ID   | Type              | Title                              |
|------|-------------------|------------------------------------|
| B001 | Book              | Introduction to Algorithms         |
| B002 | Book              | Clean Code                         |
| B003 | Book              | The Art of Computer Programming    |
| J001 | Journal           | IEEE Transactions on Computers     |
| J002 | Journal           | ACM Computing Surveys              |
| E001 | Digital Equipment | Laptop Dell XPS 15                 |
| E002 | Digital Equipment | iPad Pro                           |

---

## Business Rules

- **Students** can borrow up to **5 items** at a time
- **Faculty** members can borrow up to **10 items** at a time
- Default loan period is **14 days** (configurable at checkout)
- Overdue fine is **Rs. 10 per day** past the due date
- Fines are calculated automatically on return or viewable in real time

---

## Limitations & Known Issues

- Data is stored **in-memory only** — all data resets on program exit
- Passwords are stored and displayed in **plain text** (not suitable for production)
- No persistent storage (no database or file I/O)
- Single-user session at a time (no concurrency support)

---

## Possible Improvements

- Add file or database persistence (e.g. SQLite, serialization)
- Implement password hashing
- Add a renewal/extension feature for active loans
- Build a GUI using JavaFX or Swing
- Add email notifications for due dates

---

## License

This project is intended for academic/educational use.
