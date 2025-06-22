# 🐞 Bug Tracking System (BTS)

A monolithic, secure, and scalable Bug Tracking System built with Spring Boot, MySQL, and secured via JWT. Designed to efficiently track, assign, and resolve software bugs with role-based access for Admins, Developers, and Users.

---

## 🚀 Features

- ✅ **JWT Authentication**
- 👥 **Role-based Access Control**: `Admin`, `Developer`, `User`
- 🐛 **Bug Management**
  - Add / Update / Delete Bugs
  - Assign Bugs to Developers
  - View Bugs by Project or Assignee
- 📁 **Project Management**
  - Create / Read / Update / Delete Projects
- 💬 **Commenting System**
  - Add / Edit / Delete Comments on Bugs
  - View Comments by Bug/User
- 👤 **User Management**
  - Update User Roles
  - List & Fetch Users
- 📈 **Track Bug Progress by Status**

---

## 🧰 Tech Stack

| Layer         | Technology         |
|---------------|--------------------|
| 🖥 Backend     | Java, Spring Boot   |
| 🗄 Database    | MySQL              |
| 🔐 Security    | JWT (JSON Web Token) |
| ⚙️ Framework   | Spring Security, Spring MVC |
| 📦 Build Tool  | Maven              |
| 🔌 API Format  | RESTful APIs       |

---

## 📐 Architecture

- **Monolithic Architecture**
- **Secure API Gateway Layer (JWT)**
- **Clear Role Separation in Logic**

### 📊 Class Diagram

![Class Diagram](https://github.com/ShahuDev/BTS/blob/main/assets/BTSClassDiagram.drawio.png)

---

## 👤 User Roles and Use Cases

### 👨‍💻 Admin
- Manage Users, Roles, Projects, and Bugs
- Assign Bugs to Developers
- View all comments and bugs

### 🧑‍🔧 Developer
- View assigned bugs
- Comment on and update bug status
- Access project and bug data

### 👤 User
- Report bugs
- Add/view comments
- Track project bugs

### 🗺️ Use Case Flow

![Use Case Diagram](https://github.com/ShahuDev/BTS/blob/main/assets/BTSUser.jpg)

---

## 🔌 Core API Endpoints

### 🔐 Auth
- `POST /auth/login` — Authenticate user and return JWT
- `POST /auth/signup` — Register new user

### 👤 User APIs
- `GET /users` — List all users (Admin only)
- `GET /users/{id}` — Get user by ID
- `PUT /users/{id}/role?role=DEVELOPER` — Update user role (Admin only)

### 🐛 Bug APIs
- `POST /bugs/create` — Create bug (All roles)
- `GET /bugs/listAll` — List all bugs
- `GET /bugs/{id}` — Get bug by ID
- `PUT /bugs/{id}?status=IN_PROGRESS&assignee=2` — Update bug status/assignee
- `DELETE /bugs/{id}` — Delete bug (Admin only)
- `GET /bugs/listByProjects/{projectId}` — Filter bugs by project
- `GET /bugs/listByAssignedDeveloper/{developerId}` — Filter bugs by developer

### 💬 Comment APIs
- `POST /comments/create` — Add comment
- `GET /comments/getCommentById/{id}` — View comment by ID
- `PUT /comments/updateComment/{id}` — Update comment
- `DELETE /comments/deleteComment/{id}` — Delete comment (Developer only)
- `GET /comments/bug/{id}` — Get all comments of a bug
- `GET /comments/user/{id}` — Get all comments of a user
- `GET /comments/all` — Get all comments

### 📁 Project APIs
- `POST /projects/create` — Create project (Admin only)
- `GET /projects/all` — Get all projects
- `GET /projects/id/{id}` — Get project by ID
- `PUT /projects/update/{id}` — Update project (Admin only)
- `DELETE /projects/delete/{id}` — Delete project (Admin only)

---

## 🔐 JWT Security

All endpoints (except `/auth/*`) are protected via JWT. Use the JWT token in the `Authorization` header as:



---

## 🧪 Sample Test Cases

<details>
<summary>📬 Postman Collection (Click to Expand)</summary>

📥 [Download Postman Collection](assets/BugTrackingSystem.postman_collection.json)

**How to Use:**
1. Open Postman.
2. Click `Import`.
3. Select the `.json` file from the `assets` folder.
4. Start testing the APIs with provided headers (e.g., JWT).

</details>





## 💡 Future Enhancements

- [ ] Bug severity & duplicate prediction using ML
- [ ] Email notifications
- [ ] Audit logs for bug history
- [ ] UI with Angular or React

---

## 📁 Project Structure

src/
├── main/
│   ├── java/com/Dev/Bug_Tracking_System2/
│   │   ├── Controller/      # REST Controllers (User, Bug, Comment, Project)
│   │   ├── Service/         # Business logic for each module
│   │   ├── Repository/      # JPA repositories for DB access
│   │   ├── Model/           # Entity classes (User, Bug, Comment, Project)
│   │   ├── dtos/            # DTOs for request and response mapping
│   │   ├── Security/        # JWT filters, token provider, user auth config
│   │   ├── Exceptions/      # Custom exceptions and handlers
│   │   ├── AdminInitializer.java  # Preloads admin user if not present
│   │   └── BugTrackingSystem2Application.java  # Main Spring Boot entry point
│   └── resources/
│       ├── application.properties  # DB, JWT, and other configs
│       └── static/, templates/     # (If using frontend templates)
├── test/
│   └── java/com/Dev/Bug_Tracking_System2/  # Unit and integration tests



---

## 🙌 How to Contribute

1. Fork this repo
2. Create your branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -m 'Add feature'`
4. Push to the branch: `git push origin feature-name`
5. Open a Pull Request 🚀

---

## 🧑 Author

**Devansh Shahu**  
📧 [shahudevansh@gmail.com](mailto:shahudev51@gmail.com)  
🔗 [GitHub](https://github.com/ShahuDev)  
🔗 [LinkedIn](https://www.[linkedin.com/in/devansh-shahu](https://www.linkedin.com/in/devansh-shahu-a8b06824a/))

---

> ⭐ Star this repo if you find it helpful!

