# 📚 Student Study Planner & Task Manager

A web-based application developed to help students organize and manage their academic tasks efficiently.

## ✨ Features

- **Add Tasks:** Create study tasks with a title, subject, due date, and description.
- **View Tasks:** Display saved tasks in an organized table.
- **Complete Tasks:** Update task status from Pending to Completed.
- **Delete Tasks:** Remove tasks that are no longer required.
- **Database Storage:** Store task details in a MySQL database.

## 🛠️ Technologies Used

- Java (JDK 17)
- JSP (JavaServer Pages)
- Java Servlets
- JDBC
- MySQL
- HTML and CSS
- Apache Tomcat 11
- Eclipse IDE
- Maven

## ⚙️ Project Setup

1. Clone or download this repository.
2. Import the project into Eclipse as an existing Maven project.
3. Create a MySQL database named `study_planner`.
4. Create the required `tasks` table.
5. Configure the database connection in `DBConnection.java` using your local MySQL credentials.
6. Update Maven dependencies and configure Apache Tomcat 11.
7. Run the project on the Tomcat server.
8. Open the application in your web browser.

## 🗂️ Main Project Components

- `DBConnection.java` — Establishes the database connection.
- `Task.java` — Represents task information.
- `TaskServlet.java` — Handles task operations.
- `index.jsp` — Redirects users to the task page.
- `tasks.jsp` — Displays the task form and task list.
- `pom.xml` — Manages Maven dependencies and project configuration.

## 🎯 Project Objective

The main objective is to provide a simple and user-friendly platform for managing academic tasks, tracking deadlines, and monitoring task completion.

## 🚀 Future Enhancements

- User login and registration
- Deadline reminders
- Subject-wise task filtering
- Calendar view and progress reports

## 👩‍💻 Developed As

An academic project for MCA – Cloud Computing & DevOps.

---

**Note:** Configure your own MySQL credentials before running the application. Never upload real passwords or other sensitive information to GitHub.
