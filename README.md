# Phase One Capstone Project
### Building Logic-Driven Applications with Java OOP & Collections

---

## Project Overview

This project is a University Management System built using Java. It simulates a real-world academic system where students can be registered, enrolled in courses, and their academic records tracked. The system is built using core Java OOP principles, Collections, Exception Handling, and File Persistence.

---

## Learning Outcomes

- Applied OOP principles (inheritance, polymorphism, encapsulation) to model academic hierarchies
- Mastered Java Collections (List, Map) to manage complex relationships between students and courses
- Implemented Custom Exception Handling to manage business logic errors
- Developed File Persistence using java.io to save and load system states without a database
- Practiced Git/GitHub collaboration through branching and pull requests

---

## Project Structure

```
src/
├── University/
│   ├── Person.java                        # Abstract base class
│   ├── Student.java                       # Abstract student class
│   ├── Instructor.java                    # Instructor class
│   ├── Course.java                        # Course class
│   ├── UndergraduateStudent.java          # Flat rate tuition
│   └── GraduateStudent.java              # Per credit + research fee tuition
├── administration/
│   └── UniversityManager.java             # Central controller
├── exceptions/
│   ├── CourseFullException.java           # Thrown when course is full
│   └── StudentAlreadyEnrolledException.java  # Thrown when student already enrolled
├── filemanager/
│   └── FileManager.java                   # Handles file persistence
└── Main.java                              # Console interface
```

---

## Lab 1 - Object-Oriented Design and Domain Modeling

### Class Hierarchy

```
Person (abstract)
└── Student (abstract)
    ├── UndergraduateStudent  →  flat rate tuition
    └── GraduateStudent       →  per credit + research fee tuition

Instructor extends Person
Course (standalone)
```

### Key Concepts Applied

**Encapsulation** - All fields are private with public getters and setters, ensuring controlled access to data.

**Inheritance** - Student and Instructor both extend Person, sharing common fields like name and id while adding their own specific fields.

**Abstraction** - Person and Student are abstract classes. They serve as templates that cannot be instantiated directly since every person must be either a Student or Instructor, and every student must be either Undergraduate or Graduate.

**Polymorphism** - The calculateTuition() method is overridden differently in UndergraduateStudent (flat rate) and GraduateStudent (per credit rate plus research fee).

### Collections Used

- List<Student> in Course - maintains the roster of enrolled students
- Map<Course, Double> in Student - tracks each course a student is enrolled in along with their grade

---

## Lab 2 - Business Logic and Exception Handling

### UniversityManager

The central controller of the system. It provides the following methods:

- registerStudent() - adds a student to the system
- createCourse() - adds a course to the system
- enrollStudentInCourse() - enrolls a student in a course with full exception handling
- getAverageGPAByDepartment() - calculates average GPA for a department using Java Streams
- getTopStudent() - finds the highest GPA student using Java Streams

### Custom Exceptions

Two custom checked exceptions were created to handle business rule violations:

- CourseFullException - thrown when a course has reached its maximum capacity
- StudentAlreadyEnrolledException - thrown when a student tries to enroll in a course they are already in

These exceptions are caught in the main application loop to prevent crashes and provide meaningful error messages to the user.

---

## Lab 3 - Persistence and Final Integration

### FileManager

Handles saving and loading data between sessions using CSV files:

- saveStudents() - saves all students to a CSV file, including their type (Undergraduate or Graduate)
- saveCourses() - saves all courses to a CSV file
- loadStudents() - reads the CSV and recreates the correct student type using instanceof logic
- loadCourses() - reads the CSV and recreates course objects

### Console Menu

A menu-driven interface that ties all components together:

```
1. Register Student
2. Create Course
3. Enroll Student in Course
4. View Student Record
5. Generate Dean's List
6. Save and Exit
```

Input validation was added to prevent crashes when users enter invalid data, for example entering letters instead of a number for GPA.

---

## Key Java Concepts Used

| Concept | Where Used |
|---|---|
| abstract | Person and Student classes |
| extends | Student, Instructor, UndergraduateStudent, GraduateStudent |
| @Override | calculateTuition() in UndergraduateStudent and GraduateStudent |
| throws | enrollStudentInCourse() in UniversityManager |
| instanceof | FileManager when saving student type |
| try/catch | Exception handling in Main and FileManager |
| Stream | getAverageGPAByDepartment() and getTopStudent() |
| BufferedWriter/Reader | FileManager for reading and writing CSV files |

---

## Git Branching Strategy

Each lab was developed on its own branch and merged into main upon completion.

```
main
├── lab1  →  Lab 1 classes  →  merged to main
├── lab2  →  Lab 2 classes  →  merged to main
└── lab3  →  Lab 3 classes  →  merged to main
```

This approach simulates a real-world development workflow where features are developed in isolation and integrated into the main codebase once complete.

---

## How to Run

1. Clone the repository:
```bash
git clone https://github.com/lucie-engineer/Phase-One-Capstone-Project.git
```

2. Open the project in IntelliJ IDEA

3. Right click on the src folder, select "Mark Directory as" then "Sources Root"

4. Run Main.java

5. Use the console menu to interact with the system

---

## Author

Lucie
Cohort 15 - Java Backend Development
Phase 1 Capstone Project