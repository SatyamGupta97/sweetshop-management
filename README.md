# Sweet Shop Dashboard & Admin Panel

## Project Overview
This project is a **Sweet Shop Dashboard** with an **Admin Panel**, built with **React** and **Spring Boot**.  
It allows users to browse sweets, make purchases, and for admins to manage users and promotions.

---

## Features
- User registration and login
- Browse and search sweets
- Purchase sweets with stock management
- Admin Panel:
  - View all users
  - Promote users to admin
  - See total users, admin count, and regular users
- Responsive and professional UI
- Role-based access control

---

## Screenshots

### Dashboard Page
![Dashboard Page](screenshots/dashboard.png)

### Admin Panel
![Admin Panel](screenshots/admin-panel.png)

---

## My AI Usage
I leveraged AI tools during development to improve productivity and design decisions.

- **Gemini**: Used to brainstorm API endpoint structures, component layouts, and overall project architecture.
- **Claude**: Used to generate CSS for responsive and professional UI, create React component templates, and suggest improvements for Admin Panel interactions.

**Reflection:**  
AI tools significantly accelerated my workflow. They helped me think through component design, reduce boilerplate code, and quickly create polished UI layouts. While I still wrote the majority of the core logic myself, AI allowed me to experiment faster and iterate more efficiently.

---

## Tech Stack
- **Frontend:** React, Tailwind CSS  
- **Backend:** Spring Boot, MySQL  
- **Authentication:** JWT-based  
- **Deployment:** Localhost (development)

---

## Getting Started

### Prerequisites
- Node.js (v18+ recommended)
- npm or yarn
- Java 17+
- Maven
- MySQL database

### Clone the Repository
```bash
git clone <your-repo-url>
cd <your-project-folder>
```
Backend Setup
```bash
Copy code
cd backend
```
# Update application.properties with your MySQL credentials
./mvnw spring-boot:run
The backend will start at http://localhost:8080.

Frontend Setup
```bash
Copy code
cd frontend
npm install
npm start
```
The frontend will open at http://localhost:3000.

Usage
Register a new user and log in.

Browse available sweets on the Dashboard.

Admins can access the Admin Panel to manage users.

Promote users to admin as needed.

Folder Structure
text
Copy code
frontend/      # React app
backend/       # Spring Boot API
screenshots/   # UI screenshots
Notes
The first registered user can be assigned as ADMIN directly in the database if needed.

All sensitive operations are protected via JWT-based authentication.

The UI is responsive and works on both mobile and desktop.