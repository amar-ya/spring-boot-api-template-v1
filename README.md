# 🚀 Spring Boot API Starter — Auth + User + Security (JWT)

A minimal, ready-to-use **starter** for building REST APIs fast.  
It ships with a **User** entity, **signup/login** endpoints, and **JWT security** already configured — so you can start coding real features immediately.

> **Use it freely** — you’re welcome to copy, and use this template in your own projects.

---

## 🧩 Overview

- **Language/Runtime:** **Java 24**
- **Framework:** **Spring Boot 3.x** (Web, Security, Validation)
- **Auth:** Stateless **JWT** (access + refresh)
- **Validation:** **Jakarta Validation** (`@NotBlank`, `@Email`, …)
- **Persistence:** Spring Data JPA (configure your DB in `application.yml`)
- **Migrations:** **Liquibase**
- **Boilerplate reduction:** **Lombok**

---

## ✨ What’s Included

- 👤 **User model** (email, password hash, roles)
- 🔐 **Auth flow:** `POST /auth/signup`, `POST /auth/login`, `POST /auth/refresh`
- 🛡️ **Security config** protecting `/api/**` by default (Bearer token)
- 🧱 **Starter structure** ready for your domain modules

--- 

## Note 

- changing the JWT secret in the application.properties to a random HS256
  is smart step since it may cause some problem if you used the same one later


---

## ⚙️ Quick Start

```bash
# 1) Clone
git clone https://github.com/amar-ya/spring-boot-api-template-v1
cd https://github.com/amar-ya/spring-boot-api-template-v1

# 2) Configure application.xml (DB + JWT secret)

# 3) Run
./mvnw spring-boot:run
# or
./gradlew bootRun

