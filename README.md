# 🏥 Boost Physio Clinic – Booking System

> A console-based appointment management system developed in **Java** for the **7COM1025 – Programming for Software Engineers** module at the **University of Hertfordshire**.

---
## check drive for documentation
https://drive.google.com/drive/folders/1GsfzF_kIZePYGxZ7rOZLYXJSJbxmB_I5?usp=drive_link
--
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
  ![WhatsApp Image 2025-04-24 at 19 38 29_33ad1251](https://github.com/user-attachments/assets/05fe8bc1-f2bb-4c8c-adfe-8c5e72c832f1)
  
- ✅ Adding patient
  ![image](https://github.com/user-attachments/assets/ba6ccc09-abd4-46e5-a18d-eb2534687931)
  
- ✅ Booking Appointment
- ![image](https://github.com/user-attachments/assets/94baaf74-1538-43cf-a6a2-3736cf31cc27)

- ✅ Final report output
  ![image](https://github.com/user-attachments/assets/135baeee-a6df-4c8d-9b3b-a893554ff7ff)

- ✅ JUnit test results
  ![WhatsApp Image 2025-04-24 at 17 37 01_929ace34](https://github.com/user-attachments/assets/f2bbaec7-41f8-48ad-afa7-7a117890272a)

---

## ✍️ Author

- **Rohan Ghadage**
- **8329503580**
- **ghadagerohan07@gmail.com**
- Student,Mumbai University

---
