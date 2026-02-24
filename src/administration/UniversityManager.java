package administration;

import University.Course;
import University.Student;
import exceptions.CourseFullException;
import exceptions.StudentAlreadyEnrolledException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UniversityManager {
    private final List<Student> students = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();


    public void registerStudent(Student student) {
        students.add(student);
    }


    public void createCourse(Course course) {
        courses.add(course);
    }


    public void enrollStudentInCourse(Student student, Course course)
            throws CourseFullException, StudentAlreadyEnrolledException {


        if (course.getRoster().size() >= course.getMaxCapacity()) {
            throw new CourseFullException("Course " + course.getCourseName() + " is full!");
        }


        if (course.getRoster().contains(student)) {
            throw new StudentAlreadyEnrolledException("Student " + student.getName() + " is already enrolled!");
        }


        course.addStudent(student);
    }


    public List<Student> getStudents() {
        return students;
    }


    public List<Course> getCourses() {
        return courses;
    }


    public double getAverageGPAByDepartment(String department) {
        return students.stream()
                .filter(s -> s.getDepartment().equals(department))
                .mapToDouble(Student::getGPA)
                .average()
                .orElse(0.0);
    }

   
    public Student getTopStudent() {
        return students.stream()
                .max(Comparator.comparingDouble(Student::getGPA))
                .orElse(null);
    }
}