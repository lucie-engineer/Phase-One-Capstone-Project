import University.Course;
import University.GraduateStudent;
import University.Student;
import University.UndergraduateStudent;
import administration.UniversityManager;
import exceptions.CourseFullException;
import exceptions.StudentAlreadyEnrolledException;
import filemanager.FileManager;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UniversityManager manager = new UniversityManager();
        FileManager fileManager = new FileManager();

        List<Student> loadedStudents = fileManager.loadStudents("students.csv");
        List<Course> loadedCourses = fileManager.loadCourses("courses.csv");

        for (Student s : loadedStudents) manager.registerStudent(s);
        for (Course c : loadedCourses) manager.createCourse(c);

        boolean running = true;
        while (running) {
            System.out.println("\n--------University Management System------");
            System.out.println("1. Register Student");
            System.out.println("2. Create Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. View Student Record");
            System.out.println("5. Generate Deans List");
            System.out.println("6. Save and Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter student type (U=Undergraduate, G=Graduate): ");
                    String type = scanner.nextLine();
                    Student student;
                    if (type.equalsIgnoreCase("U")) {
                        student = new UndergraduateStudent();
                    } else {
                        student = new GraduateStudent();
                    }
                    System.out.println("Enter Student ID:");
                    student.setStudentID(scanner.nextLine());
                    System.out.println("Enter Name:");
                    student.setName(scanner.nextLine());

                    System.out.println("Enter GPA:");
                    Double newGPA = null;
                    while (newGPA == null) {
                        try {
                            newGPA = scanner.nextDouble();
                            if (newGPA < 0.0 || newGPA > 4.0) {
                                System.out.println("GPA must be between 0.0 and 4.0! Try again:");
                                newGPA = null;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid GPA! Please enter a number between 0.0 and 4.0:");
                            scanner.nextLine();
                        }
                    }
                    scanner.nextLine();
                    student.setGPA(newGPA);

                    System.out.println("Enter Department:");
                    student.setDepartment(scanner.nextLine());

                    manager.registerStudent(student);
                    System.out.println("Student registered successfully!");
                    break;

                case 2:
                    Course course = new Course();
                    System.out.println("Enter Course Code: ");
                    course.setCourseCode(scanner.nextLine());
                    System.out.println("Enter Course Name:");
                    course.setCourseName(scanner.nextLine());
                    System.out.println("Enter Credits:");
                    course.setCredits(scanner.nextInt());
                    System.out.println("Enter Max Capacity:");
                    course.setMaxCapacity(scanner.nextInt());
                    scanner.nextLine();
                    manager.createCourse(course);
                    System.out.println("Course created successfully!");
                    break;

                case 3:
                    System.out.println("Enter Student ID:");
                    String studentID = scanner.nextLine();
                    System.out.println("Enter Course Code: ");
                    String courseCode = scanner.nextLine();

                    Student foundStudent = null;
                    Course foundcourse = null;

                    for (Student s : manager.getStudents()) {
                        if (s.getStudentID().equals(studentID)) {
                            foundStudent = s;
                            break;
                        }
                    }
                    for (Course c : manager.getCourses()) {
                        if (c.getCourseCode().equals(courseCode)) {
                            foundcourse = c;
                            break;
                        }
                    }

                    if (foundStudent == null) {
                        System.out.println("Student not found!");
                    } else if (foundcourse == null) {
                        System.out.println("Course not found!");
                    } else {
                        try {
                            manager.enrollStudentInCourse(foundStudent, foundcourse);
                            System.out.println("Student enrolled successfully!");
                        } catch (CourseFullException | StudentAlreadyEnrolledException e) {
                            System.out.println("Enrollment error: " + e.getMessage());
                        }
                    }
                    break;

                case 4:
                    System.out.println("Enter Student ID: ");
                    String id = scanner.nextLine();
                    for (Student s : manager.getStudents()) {
                        if (s.getStudentID().equals(id)) {
                            System.out.println("Name: " + s.getName());
                            System.out.println("GPA: " + s.getGPA());
                            System.out.println("Department: " + s.getDepartment());
                            System.out.println("Tuition: " + s.calculateTuition());
                            System.out.println("Courses: " + s.getCourseGrades());
                            break;
                        }
                    }
                    break;

                case 5:
                    System.out.println("===== Dean's List =====");
                    for (Student s : manager.getStudents()) {
                        if (s.getGPA() > 3.5) {
                            System.out.println(s.getName() + " - GPA: " + s.getGPA());
                        }
                    }
                    break;

                case 6:
                    if (manager.getStudents().isEmpty() && manager.getCourses().isEmpty()) {
                        System.out.println("Nothing to save!");
                    } else {
                        fileManager.saveStudents(manager.getStudents(), "students.csv");
                        fileManager.saveCourses(manager.getCourses(), "courses.csv");
                        System.out.println("Data saved! Goodbye!");
                    }
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }
}