package University;

public class UndergraduateStudent extends Student {
    double flatrate= 5000.0;

    public UndergraduateStudent(String name, String id, String studentID, Double GPA, String department) {
        super(name, id, studentID, GPA, department);
    }
    @Override
    public double calculateTuition() {
        return 5000.0;
    }
}