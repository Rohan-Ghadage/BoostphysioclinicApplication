# 🏥 Boost Physio Clinic – Booking System

> A console-based appointment management system developed in **Java** for the **7COM1025 – Programming for Software Engineers** module at the **University of Hertfordshire**.

---

## 🚀 Features

- 📇 Add and remove patients
- 📆 Book appointments by:
  - Area of expertise
  - Physiotherapist name
- 🔄 Change or cancel appointments
- ✅ Mark appointments as attended
- 📊 Generate end-of-term report:
  - Appointments grouped by physiotherapist
  - Sorted list of physiotherapists by attended appointments
- 🧠 In-memory storage (no database needed)
- 🧪 Unit tested with JUnit 5
- ⚙️ Built using Spring Boot, Maven, and Lombok

---

## 🧱 Technologies Used

- **Java 17**
- **Spring Boot 3.4.4**
- **Maven**
- **JUnit 5**
- **Lombok**

---

## 📁 Project Structure

```
boostphysioclinic/
├── src/
│   ├── main/
│   │   └── java/com/boostphysioclinic/
│   │       ├── model/                 # Domain classes (Patient, Physiotherapist, etc.)
│   │       ├── service/               # ClinicManager (core logic)
│   │       └── BoostphysioclinicApplication.java
│   └── test/
│       └── java/com/boostphysioclinic/
│           ├── BoostphysioclinicApplicationTests 
|           └── ClinicManagerTest.java
├── pom.xml
├── README.md
```

---

## 🛠️ Getting Started

### ✅ Build the JAR

Navigate to your project root:

```bash
mvn clean package -DskipTests
```

### ▶️ Run the Application

```bash
java -jar target/boostphysioclinic-0.0.1-SNAPSHOT.jar
```

You'll now be able to interact with the application through the command line menu.

---

## 🧪 Run Unit Tests

```bash
mvn test
```

This runs 5 key JUnit tests covering logic like booking, conflicts, and patient management.

---

## 📸 Screenshots

- ✅ Console interaction sample
- ✅ Booking and cancellation
- ✅ Final report output
- ✅ JUnit test results

> You can paste your screenshots here as `.png` images once captured.

---

## ✍️ Author

**Rohan Ghadage**
**8329503580**
**ghadagerohan07@gmail.com**
Student,Mumbai University

---
