package University;

import java.util.HashMap;
import java.util.Map;

public abstract class Student extends Person{
   private String studentID;
   private  Double GPA;
   private String department;
   private Map<Course, Double> courseGrades = new HashMap<>();


   public String getStudentID(){
       return studentID;
   }


   public Double getGPA(){
       return GPA;
   }

   public String getDepartment(){
       return department;
   }

   public Map<Course, Double> getCourseGrades() {
        return courseGrades;
    }

   public void setStudentID(String studentID){
       this.studentID=studentID;
   }
   public void setGPA(Double GPA){
       this.GPA=GPA;
   }
   public void  setDepartment(String department){
       this.department=department;
   }
   public void addCourseGrade(Course course, Double grade){
       courseGrades.put(course, grade);
   }
   public abstract double calculateTuition();
}
