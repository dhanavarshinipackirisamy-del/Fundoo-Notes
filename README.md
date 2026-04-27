# 📝 Fundoo Notes Backend

A robust, enterprise-grade Spring Boot backend for the Fundoo Notes application.

---

## 🏗️ Architecture & Development

This project is developed using a strict **Use-Case (UC) driven approach**. It maintains a **clean Git workflow** with:

* 🔹 `main` → Documentation only
* 🔹 `develop` → Code integration
* 🔹 `feature/UCX` → Individual feature development

All APIs follow a **layered architecture pattern**:

```
Client ➡️ Controller ➡️ DTO ➡️ Service ➡️ Repository ➡️ Database
```

This ensures:

* Clear separation of concerns
* Maintainable and scalable code
* Industry-standard backend structure

---

# ✅ Phase 1 — Core Backend (UC1 – UC10)

### 🔹 UC1: Setup & Packages

Established Spring Boot 3 project with a clean package structure.

### 🔹 UC2: Database Connectivity

Configured MySQL using `application.yml` with Hibernate auto DDL.

### 🔹 UC3: User Persistence

Created `User` entity and `UserRepository` using Spring Data JPA.

### 🔹 UC4: User Registration

Implemented registration API with DTO validation and global exception handling.

### 🔹 UC5: Authentication

Added password encryption using BCrypt and JWT token generation.

### 🔹 UC6: JWT Validation Pipeline

Developed JWT filter to secure APIs and manage authentication context.

### 🔹 UC7: Note Entity Mapping

Designed `Note` entity with optimized structure using `userId` instead of direct relation.

### 🔹 UC8: Note Creation

Built secure API to create notes linked to authenticated user.

### 🔹 UC9: Note Dashboard Fetch

Implemented API to fetch user-specific notes with secure access control.

### 🔹 UC10: Toggle Controllers

Created APIs to toggle note states:

* 📌 Pin
* 📦 Archive
* 🗑️ Trash

---

# 🚀 Phase 2 — Advanced Backend (UC11 – UC16)

### 🔹 UC11: Aspect-Oriented Programming (AOP)

Implemented logging, execution tracking, and cross-cutting concerns using Spring AOP.

### 🔹 UC12: RabbitMQ Asynchronous Messaging

Designed event-driven architecture using Producer–Consumer pattern for async processing.

### 🔹 UC13: Redis Caching (JWT + OTP + TTL)

Integrated Redis for:

* Token caching
* OTP storage
* Expiry handling

### 🔹 UC14: Spring Cache Optimization

Improved performance using caching strategies with proper eviction policies.

### 🔹 UC15: JMS Messaging (ActiveMQ)

Implemented queue-based messaging using JMS for conceptual understanding of messaging systems.

### 🔹 UC16: Spring Batch (Excel Processing)

Developed batch processing pipeline to:

* Import Excel data
* Process bulk records
* Persist efficiently

---

# 🛠️ Tech Stack

* **Backend Framework**: Spring Boot 3
* **Language**: Java 17
* **Database**: MySQL
* **ORM**: Spring Data JPA / Hibernate
* **Security**: Spring Security + JWT
* **Messaging**: RabbitMQ, ActiveMQ (JMS)
* **Caching**: Redis
* **Batch Processing**: Spring Batch
* **Build Tool**: Maven

---

# 🔄 Git Workflow

```
main        → Documentation
develop     → Integration branch
feature/*   → Individual UC implementation
```

Example:

```
feature/UC1-project-setup
feature/UC2-database-config
...
feature/UC16-batch-processing
```

---

# 🚀 Getting Started

### 1️⃣ Clone the Repository

```
git clone <your-repo-url>
cd fundoo-notes
```

### 2️⃣ Run Application

```
mvn clean install
mvn spring-boot:run
```

### 3️⃣ Access API

```
http://localhost:8080
```

---

# 🎯 Learning Outcome

This project is designed to help understand:

* Layered architecture design
* Secure authentication using JWT
* Database modeling with JPA
* REST API development
* Caching and performance optimization
* Asynchronous messaging systems
* Batch processing for large datasets

---

# 📌 Future Enhancements

* Label system
* Reminder scheduling
* Notification service
* File attachments
* Microservices with Spring Cloud

---

# 💯 Final Note

This is not just a CRUD project —
it is a **complete backend system design practice** aligned with real-world enterprise applications.

---
