# 🍽️ QrMenuApp (Backend)

**QrMenuApp** is a backend service built with **Java Spring Boot** that powers a digital restaurant menu system.  
It enables restaurants to manage their menus, items, and QR codes efficiently — all images are stored as **Base64 strings**, eliminating the need for external file storage.

---

## 🚀 Features

- 🔐 **Authentication & Authorization** — Secure login and role-based access control (Admin, Company, User).  
- 🧾 **Menu Management** — Create, update, and delete menus and menu items easily.  
- 🖼️ **Base64 Image Storage** — All images are stored as Base64 strings in the database, ensuring fast retrieval and easy integration with the frontend.  
- 📱 **QR Code Generator** — Generates unique QR codes for each company or menu, allowing customers to access menus instantly.  
- 🏢 **Multi-Company Support** — Each company manages its own menu and data in isolation.  
- 💬 **Instant Updates** — Changes to menus or items are immediately visible when accessed via QR code.  
- 📂 **Database Integration** — Fully integrated with a relational database using **Spring Data JPA**.  
- 🌐 **RESTful API** — Clean and well-structured API endpoints for seamless communication with the frontend.  
- 🧠 **DTO Layer** — Uses Data Transfer Objects for efficient and secure data transfer between layers.

---

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **Lombok**
- **Maven**
- **MySQL**
- **ZXing Library** (for QR code generation)

---

## ⚙️ Installation & Setup

### 1. Clone the repository
```bash
git clone https://github.com/mertcakirm/QrMenuApp.git
