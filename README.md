
# 🎓 Student Management System — Java (From Scratch)

This is my complete backend project built fully in **Core Java**, without Spring Boot.  
The goal was to understand how Java works internally while building a real backend with:

- HTTP server (Java's built-in `HttpServer`)
- MongoDB persistence
- REST endpoints
- Metrics + Monitoring
- OOP design
- Streams, Lambdas, Optional, LocalDate
- Docker + Railway deployment

---

# 🌍 Live Application

### ▶️ **Index Endpoint**  
https://studymanagementsystem-production.up.railway.app/

### 📊 **Metrics Endpoint**  
Exposed endpoints for monitoring:  
[Metrics](https://studymanagementsystem-production.up.railway.app/metrics)

---

# 📚 Documentation (All Files in `/docs`)

| Document | Link |
|---------:|------|
| API Documentation | [docs/API_Documentation.md](docs/API_Documentation.md) |
| JVM Architecture Report | [docs/JVM_Architecture_Report.md](docs/JVM_Architecture_Report.md) |
| LLD Documentation | [docs/LLD_Documentation.md](docs/LLD_Documentation.md) |
| Setup Instructions | [docs/Setup_Instructions.md](docs/Setup_Instructions.md) |
| System Documentation | [docs/System_Documentation.md](docs/System_Documentation.md) |

> The assignment requirements and rubric are taken from the project brief (see PDF). :contentReference[oaicite:0]{index=0}

---

# 📘 Problem Statement (My Understanding)

> *“Build a clean, object-oriented backend system that can manage students, courses, and enrollments — with correct OOP modeling, CRUD operations, validation, and optional advanced features. The project should demonstrate solid Java fundamentals while also being extendable into a real backend.”*

This is based directly on the course assignment brief. :contentReference[oaicite:1]{index=1}

---

# ✅ Requirements Checklist (From PDF + What I Implemented)

Below is the full checklist with ✔ indicating what *my project actually covers*.

### **Core Java + OOP (Required)**  
- ✔ Encapsulation  
- ✔ Inheritance (Person → Student → GraduateStudent)  
- ✔ Polymorphism (overriding + overloading)  
- ✔ Abstraction (interfaces Searchable + Gradeable)  
- ✔ Access modifiers demonstration  
- ✔ Constructors (default + parameterized + chaining)  
- ✔ JVM Architecture Report (separate doc) :contentReference[oaicite:2]{index=2}

### **Java Fundamentals (Required)**  
- ✔ Primitive types  
- ✔ Typecasting  
- ✔ Static vs instance  
- ✔ Scope demonstration  
- ✔ Proper packaging  
- ✔ FQCN usage  
- ✔ IDE + environment setup documented

### **Backend Requirements (Required)**  
- ✔ Console logic + service classes  
- ✔ CRUD for students  
- ✔ CRUD for courses  
- ✔ Enrollments  
- ✔ Validation  
- ✔ Custom exceptions

### **Advanced Java Features (Bonus)** :contentReference[oaicite:3]{index=3}  
- ✔ Streams  
- ✔ Lambdas  
- ✔ Optional  
- ✔ LocalDate / LocalTime

### **Design Patterns (Bonus)**  
- ✔ Singleton (MongoDBManager, DateTimeUtil)  
- ✔ Builder (ResponseBuilder)  
- ❌ Factory (not used)  
- ❌ Observer (not used)

### **Exception Handling & File I/O (Bonus)** :contentReference[oaicite:4]{index=4}  
- ✔ Custom exceptions  
- ❌ File I/O (we implemented DB instead — more advanced)

### **AI Integration (Bonus)**  
- ❌ Not used

### **Extras I Implemented**  
- ✔ Fully functional REST HTTP server  
- ✔ MongoDB persistence (Atlas)  
- ✔ Custom JSON builder (ResponseBuilder)  
- ✔ Metrics endpoint (`/metrics`)  
- ✔ Deployment via Docker + Railway  
- ✔ High-level architecture + LLD diagrams  
- ✔ API documentation  
- ✔ Publicly accessible API

---

# 🧩 Quick Architecture Preview

```mermaid
flowchart TD

Client --> RS["RestServer"]
RS --> Students["/students"]
RS --> Courses["/courses"]
RS --> Enrollments["/enrollments"]
RS --> Metrics["/metrics"]

Students --> StudentService
Courses --> CourseService
Enrollments --> EnrollmentService

StudentService --> MongoDBManager
CourseService --> MongoDBManager
EnrollmentService --> MongoDBManager

MongoDBManager --> DB["MongoDB Atlas"]

StudentService --> Utils
CourseService --> Utils
EnrollmentService --> Utils
````

---

# 🧱 Entities Overview

* **Person** — base class
* **Student extends Person** — year, student-specific fields
* **GraduateStudent extends Student** — thesisTopic, overrides
* **Course** — course metadata
* **Enrollment** — studentId, courseId, enrolledOn (bridge entity)

---

# 🚀 Running the Project Locally

### Compile

```bash
javac -d out $(find src -name "*.java")
```

### Run

```bash
java com.airtripe.studymanagement.main.RestServer
```

Server starts at:

```
http://localhost:8080/
```

---

# 🧪 Quick API Tests

### Students

```bash
curl https://studymanagementsystem-production.up.railway.app/students
```

### Metrics

```bash
curl https://studymanagementsystem-production.up.railway.app/metrics
```

### Add student

```bash
curl -X POST -H "Content-Type: application/json" \
-d '{"id":"S1","name":"John","email":"x@y.com","year":2}' \
https://studymanagementsystem-production.up.railway.app/students
```

---

# 📘 Tech Stack

* Java 21 (Temurin)
* com.sun.net.httpserver.HttpServer
* MongoDB Atlas
* Docker
* Railway
* Mermaid (UML diagrams)
* IntelliJ IDEA

---

# 🧠 Final Notes

This project connects Java theory (OOP, JVM) with real backend engineering (REST, persistence, deployment). The docs in `docs/` contain the detailed writeups, diagrams, and how-to guides.

---
