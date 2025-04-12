# 🐞 Bug Tracking System (BTS) - Backend

A Spring Boot RESTful backend for managing bugs, projects, users, and comments in software development teams.

---

## 🚀 Features

- Bug reporting and status tracking
- Project creation and assignment
- Role-based user authentication (Admin/User)
- Comment system for collaboration
- Secured REST APIs with Spring Security and `@PreAuthorize`

---

## 🧰 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Maven

---

## 🔐 Roles & Access

| Role  | Permissions |
|-------|-------------|
| ADMIN | Full access: Create/assign projects, users, bugs |
| USER  | Report bugs, comment, update assigned bugs |

---

## 📂 Project Structure

src/ └── main/java/com/devDevs/Bug/Tracking/system/ ├── Controllers ├── Models ├── Repositories ├── Services └── Security


---

## ⚙️ Setup Instructions

### 1. Clone the Repository

git clone https://github.com/your-username/BTS.git
cd BTS


spring.datasource.url=jdbc:mysql://localhost:3306/bug_tracker
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
⚠️ Create the bug_tracker database manually in MySQL before running.

### Run the application using maven
./mvnw spring-boot:run


🔑 Security
Authentication: Basic Auth

Authorization: @PreAuthorize for method-level control

Passwords are stored using BCryptPasswordEncoder

🧪 Sample API Endpoints
Method	Endpoint	Description	Role
POST	/bugs/create	Create a new bug	ADMIN
GET	/bugs/user/{id}	Get bugs by user ID	USER
POST	/projects/create	Create new project	ADMIN
POST	/comments/add	Add comment	Auth
GET	/user/me	Get current user profile	Auth
Use tools like Postman to test with Basic Auth headers.

🛠️ Future Enhancements
JWT-based authentication

Swagger API documentation

Unit + Integration testing

Dockerization

Shahu Dev
GitHub: @ShahuDev

📄 License
This project is open-source and available under the MIT License.

yaml
Copy
Edit

---
