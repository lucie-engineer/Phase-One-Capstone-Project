package University;

import java.util.ArrayList;
import java.util.List;

public class Course {
        private String courseName;
        private String courseCode ;
        private int  credits;
        private List<Student> roster = new ArrayList<>();

        public List<Student> getRoster() {  // ← add here
            return roster;
        }
        public String getCourseName() {
            return courseName;
        }

        public String getCourseCode() {
            return courseCode;
        }
        public int getCredits(){
            return credits;
        }

        public void setCourseName(String courseName) {
            this.courseName= courseName;
        }

        public void setCourseCode(String courseCode ) {
            this.courseCode = courseCode;
        }
         public void  setCredits( int credits){
            this.credits = credits;
         }
        public void addStudent(Student student) { // ← added
        roster.add(student);
        }
    }

