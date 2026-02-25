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

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        UniversityManager manager= new UniversityManager();
        FileManager fileManager= new FileManager();

        List<Student> loadedStudents = fileManager.loadStudents("students.csv");
        List<Course> loadedCourses = fileManager.loadCourses("courses.cvs");

        for (Student s : loadedStudents) manager.registerStudent(s);
        for (Course c : loadedCourses) manager.createCourse(c);

        boolean running = true;
        while (running){
            System.out.println("\n--------university Management System------");
            System.out.println("1.Register Student");
            System.out.println("2.Create Course");
            System.out.println("3.Enroll Student in Course");
            System.out.println("4.View Student Record");
            System.out.println("5. Generate Deans list");
            System.out.println("6.save and Exit");
            System.out.println("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.println("Enter student type (U=Undergraduate, G=Graduate): ");
                    String type = scanner.nextLine();
                    Student student;
                    if (type.equalsIgnoreCase("U")){
                        student = new UndergraduateStudent();
                    }
                    else {
                        student = new GraduateStudent();
                    }
                    System.out.println("Enter Student ID:");
                    student.setStudentID(scanner.nextLine());

                    System.out.println("Enter Name:");
                    student.setName(scanner.nextLine());

                    System.out.println("Enter GPA");
                    student.setGPA(scanner.nextDouble());
                    scanner.nextLine();

                    System.out.println("Enter Department");
                    student.setDepartment(scanner.nextLine());
                    manager.registerStudent(student);
                    System.out.println("Student registered successfuly");
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
                    System.out.println("Course created successfuly");
                    break;

                case 3:
                    System.out.println("Enter Student ID:");
                    String studentID = scanner.nextLine();

                    System.out.println("Enter Course Code: ");
                    String courseCode = scanner.nextLine();


                    Student foundStudent = null;
                    Course foundcourse = null;

                    for (Student s : manager.getStudents()){
                        if (s. getStudentID().equals(studentID)){
                            foundStudent = s;
                            break;
                        }
                    }

                    for (Course c : manager.getCourses()){
                        if (c.getCourseCode().equals(courseCode)){
                            foundcourse = c;
                            break;
                        }
                    }

                    if (foundStudent == null){
                        System.out.println("Student not found");
                    }
                    else if (foundcourse == null){
                    System.out.println("Course not found");
                }
                    else {
                        try{
                            manager.enrollStudentInCourse(foundStudent, foundcourse);
                            System.out.println("Student enrolled successfully");
                        }
                        catch (CourseFullException | StudentAlreadyEnrolledException e){
                            System.out.println("Enrollment error:" + e.getMessage());
                        }
                    }
                    break;

                case 4:
                    System.out.println("Eneter Student ID: ");
                    String id = scanner.nextLine();
                    for (Student s : manager.getStudents()){
                        if(s.getStudentID().equals(id)){
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
                    System.out.println("Deans List");
                    for (Student s : manager.getStudents()) {
                        if (s.getGPA() > 3.5) {
                            System.out.println(s.getName() + " - GPA: " + s.getGPA());
                        }
                    }
                    break;

                case 6:
                    fileManager.saveStudents(manager.getStudents(), "students.csv");
                    fileManager.saveCourses(manager.getCourses(), "courses.csv");
                    System.out.println("Data saved");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option, Please try again");
            }
        }
    }
}