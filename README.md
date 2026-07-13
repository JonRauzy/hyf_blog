### FINAL PROJECT HYF BACK END JAVA 2026

# 📝 **HyF Blog API** – Modern RESTful Blog Backend

[![Java 25](https://img.shields.io/badge/Java-25-orange.svg?logo=openjdk)](https://openjdk.org/)
[![Spring Boot 4.0.6](https://img.shields.io/badge/Spring%20Boot-4.0.6-brightgreen.svg?logo=spring)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg?logo=mysql)](https://www.mysql.com/)
[![JWT](https://img.shields.io/badge/JWT-0.12.6-black.svg)](https://jwt.io/)
[![Lombok](https://img.shields.io/badge/Lombok-1.18.30-purple.svg)](https://projectlombok.org/)

> **Training Project for HackYoourFuture Belgiul**: Secure backend for a blog with article, user, and comment management, featuring JWT authentication.

---

## ✨ **Features**
✅ **Article Management** (CRUD) – Title, content, author, date, tags
✅ **Authentication & Authorization** – JWT + Spring Security (`USER`/`CONTRIBUTOR`/`ADMIN` roles)
✅ **User Management** – Registration, login, profiles
✅ **Comments** – (CRUD) comments on articles
✅ **Data Validation** – `@Valid` annotations (e.g., field size, email format)
✅ **Relational Database** – MySQL + JPA/Hibernate
✅ **DevTools** – Hot-reload for rapid development

---

## 🛠 **Tech Stack**
   Layer          | Technologies                                                                 |
 |----------------|------------------------------------------------------------------------------|
| **Backend**    | Spring Boot 4.0.6 (WebMVC, Data JPA, Validation, Security)                |
| **Auth**       | JWT (`jjwt-api`, `jjwt-impl`, `jjwt-jackson`) + Spring Security 7.1.0-M3   |
| **Database**   | MySQL (`mysql-connector-j`) + JPA/Hibernate                                |
| **Tools**      | Lombok (boilerplate reduction), AspectJ (AOP)                              |
| **Tests**      | Spring Boot Test (WebMVC, JPA, Validation)                                 |
| **Build**      | Maven (Java 25)                                                             |

---

## 🚀 **Quick Start**

### Prerequisites
- [JDK 25](https://openjdk.org/projects/jdk/25/)
- [MySQL 8+](https://dev.mysql.com/downloads/) (or Docker)
- [Maven 3.6+](https://maven.apache.org/)

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/your-username/hyf_blog.git
cd hyf_blog
```

### 2⃣ Set up the DB
copy/paste "EXAMPLE-env.properties" remove "EXAMPLE-" and paste your DB login
We use MySQL by default, just switch with the proper Maven dependency corresponding to your DB driver 

### That's it
Enjoy 