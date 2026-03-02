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

                // ✅ Replace with this
                String sid = data[0];
                String sname = data[1];
                Double gpa = Double.parseDouble(data[2]);
                String dept = data[3];

                Student student;
                if (data[4].equals("G")) {
                    student = new GraduateStudent(sname, sid, sid, gpa, dept);
                } else {
                    student = new UndergraduateStudent(sname, sid, sid, gpa, dept);
                }
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
