# Connect Me - HR Management System

A comprehensive Java-based Human Resource Management System designed for "Connect Me" mobile service company to automate employee management processes using Object-Oriented Programming principles.

## 📋 Table of Contents

- [Project Overview](#-project-overview)
- [Features](#-features)
- [UML Diagrams](#-uml-diagrams)
- [OOP Concepts Implemented](#-oop-concepts-implemented)
- [System Requirements](#-system-requirements)
- [Installation & Setup](#-installation--setup)
- [User Manual](#-user-manual)
- [Project Structure](#-project-structure)
- [Technical Implementation](#-technical-implementation)

---

## 🎯 Project Overview

**Connect Me HR Management System** is a Java Swing application developed for automating HR processes including employee management, department organization, and designation tracking. The system supports two user roles: HR Manager and HR Assistant with differentiated access levels.


## ✨ Features

### HR Manager Capabilities:

- ✅ Add/Edit/Delete departments and designations
- ✅ Comprehensive employee management
- ✅ Advanced search functionality (by name, department, designation)
- ✅ HR Assistant account management
- ✅ Data persistence using file storage

### HR Assistant Capabilities:

- ✅ Employee search and viewing
- ✅ Limited access based on role permissions

### System Features:

- ✅ User authentication with role-based access
- ✅ Data validation and error handling
- ✅ File-based data persistence
- ✅ Intuitive GUI interface


## 📐 UML Diagrams

### Use Case Diagram

- **Actors**: HR Manager, HR Assistant
- **Use Cases**: Department management, Employee management, Search operations, User account management

### Class Diagram

- **Main Classes**: HRManager, HRAssistant, Employee, Department, Designation
- **Relationships**: Inheritance, Composition, Association

### Sequence Diagram

- Illustrates interaction flow between system components
- Shows message passing between objects

## 🧩 OOP Concepts Implemented

### 1. **Encapsulation**

- Private fields with public getter/setter methods
- Controlled access to object properties
- Data hiding implementation

### 2. **Inheritance**

- `HRDashboardBase` class extended by `HRManagerDashboardFrame` and `HRAssistantDashboardFrame`
- Code reuse and hierarchical organization

### 3. **Polymorphism**

- Method overriding in child classes
- Interface implementations
- Generic component usage

### 4. **Abstraction**

- Abstracted file operations through `FileHandler`
- Hidden implementation details
- Simplified interfaces for complex operations

### 5. **Classes and Objects**

- Well-defined class structures
- Object instantiation and management
- Proper class responsibilities

## ⚙️ System Requirements

### Hardware:

- Minimum 4GB RAM
- 1.5 GHz Processor

### Software:

- Windows 10/11 or compatible OS
- Java Runtime Environment (JRE) 8+
- Apache NetBeans IDE (for development)

## 🔧 Installation & Setup

### Step 1: Install Prerequisites

```bash
# Install Java JDK 8 or later
# Download and install Apache NetBeans IDE
```
### Step 2: Project Setup

1. Clone or download the project files
2. Open NetBeans IDE
3. Import Project: File → Open Project → Select project folder
4. Set Main Class: Right-click project → Properties → Run → Main Class: com.connectme.Main

### Step 3: Run the Application

1. Clean and Build: Right-click project → Clean and Build
2. Run Project: Click the Run button or press F6
3. The login screen will appear

## 📖 User Manual

### Login Instructions:

1. Launch the application
2. Select user role (HR Manager or HR Assistant)
3. Default HR Manager credentials:

&nbsp;               Username: admin
&nbsp;               Password: admin123

4. HR Assistant accounts must be created by HR Manager

### HR Manager Functions:

*Department Management:*

Navigate to "Add New Department"
Enter department details (ID, Name, Location, Contact, Description)
Data automatically saves to departments.txt

*Designation Management:*

Access "Add New Designation"
Link designations to existing departments
Set basic salary and descriptions

*Employee Management:*

Use "Add New Employee" for new hires
Complete all required fields with validation
Assign to departments and designations

*HR Assistant Account Creation:*

Go to "Manage HR Assistants"
Create login credentials for assistants
Accounts save to hr_assistants.txt

### Search Functionality:

Search employees by various criteria (name, department, designation)
Both HR Manager and Assistant can search
Real-time filtering available

## 📁 Project Structure
```
Connect-Me-HR-System/

├── src/

│   └── com/connectme/

│       ├── Main.java                 # Application entry point

│       ├── frames/                   # GUI frames

│       │   ├── LoginFrame.java

│       │   ├── HRDashboardBase.java

│       │   ├── HRManagerDashboardFrame.java

│       │   ├── HRAssistantDashboardFrame.java

│       │   ├── AddDepartmentFrame.java

│       │   ├── AddDesignationFrame.java

│       │   ├── AddEmployeeFrame.java

│       │   ├── SearchEmployeeFrame.java

│       │   └── ManageHRAssistantsFrame.java

│       ├── models/                   # Data models

│       │   ├── Employee.java

│       │   ├── Department.java

│       │   ├── Designation.java

│       │   ├── HRAssistant.java

│       │   └── User.java

│       └── utilities/               # Utility classes

│           ├── FileHandler.java

│           ├── Validator.java

│           └── IDGenerator.java

├── data files/                      # Generated data files

│   ├── departments.txt

│   ├── designations.txt

│   ├── employees.txt

│   ├── hr_assistants.txt

│   └── users.txt

└── documentation/                   # Project documentation

&nbsp;   └── st20318341_CSE4006_WRIT1.docx
```

## 💻 Technical Implementation

### Data Persistence:

File-based storage using text files
CSV-like format for easy readability
Automatic file creation if not exists

### Validation:

Field validation for required inputs
Format checking (email, phone numbers)
Data integrity maintenance

### GUI Components:

Java Swing for interface
Consistent theme and layout
Responsive design

## 🐛 Troubleshooting

### Common Issues:

1. Login Failed

Verify username/password
Check role selection
Ensure data files exist

2. File Not Found

Check if text files are in correct directory
Verify file permissions

3. Validation Errors

Fill all required fields
Check format requirements (10-digit phone numbers, valid email)

4. Application Not Starting

Verify Java installation
Check NetBeans project configuration
Ensure main class is set correctly

---

Developer: Prabath Jayasuriya
Module: CSE4006 - Object Oriented Programming
Academic Year: 2025, Semester 2
