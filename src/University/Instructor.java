package University;

public class Instructor extends Person {
    private String lectureID;
    private String department;

    public String getEmployeeID() {
        return lectureID;
    }

    public String getDepartment() {
        return department;
    }

    public void setEmployeeID(String lectureID) {
        this.lectureID = lectureID;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
