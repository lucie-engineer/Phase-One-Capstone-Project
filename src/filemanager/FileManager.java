package filemanager;

import University.Course;
import University.GraduateStudent;
import University.Student;
import University.UndergraduateStudent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public void saveStudents(List<Student> students, String filename){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for (Student student : students){
                writer.write(student.getStudentID() + "," +
                        student.getName() + "," +
                        student.getGPA()+ "," +
                        student.getDepartment() + "," +
                (student instanceof GraduateStudent ? "G" : "U"));
                writer.newLine();
            }
            System.out.println("Students saved successfully");
        }
        catch (IOException e){
            System.out.println("error saving students:"+e.getMessage());
        }
    }

    public void saveCourses(List<Course> courses, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Course course : courses) {
                writer.write(course.getCourseCode() + "," +
                        course.getCourseName() + "," +
                        course.getCredits() + "," +
                        course.getMaxCapacity());
                writer.newLine();
            }
            System.out.println("Courses saved successfully");
        }
        catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    public List<Student> loadStudents(String filename){
        List<Student> students = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader( new FileReader(filename))) {
            String line;
            while ((line =reader.readLine()) != null){
                String[] data = line.split(",");

                Student student;
                if (data[4].equals("G")){
                    student = new GraduateStudent();
                }
                else {
                    student = new UndergraduateStudent();
                }

                student.setStudentID(data[0]);
                student.setName(data[1]);
                student.setGPA(Double.parseDouble(data[2]));
                student.setDepartment(data[3]);
                students.add(student);
            }
            System.out.println("Students loaded successfully");
        }
        catch (IOException e){
            System.out.println("Error loading students:"+ e.getMessage());
        }
        return students;
    }
    public List<Course> loadCourses(String filename) {
        List<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] data = line.split(",");
                Course course = new Course();
                course.setCourseCode(data[0]);
                course.setCourseName(data[1]);
                course.setCredits(Integer.parseInt(data[2]));
                course.setMaxCapacity(Integer.parseInt(data[3]));
                courses.add(course);
            }
            System.out.println("Courses loaded successfully");
        }
        catch (IOException e){
            System.out.println("error loading courses:" + e.getMessage());
        }
        return courses;
    }
}
