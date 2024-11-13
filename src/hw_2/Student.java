package hw_2;

public class Student implements Comparable<Student> {
    private String facultyNumber;
    private String name;
    private Double gpa;

    Student(String facultyNumber, String name, Double gpa) {
        this.facultyNumber = facultyNumber;
        this.name = name;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public Double getGpa() {
        return gpa;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    @Override
    public String toString() {
        return facultyNumber + "\t" + gpa.toString() + "\t" + name;
    }

    @Override
    public int compareTo(Student other) {
        int gpaEquals = this.gpa.compareTo(other.gpa);
        if (gpaEquals == 0) {
            return this.name.compareTo(other.name);
        }
        return (-1) * gpaEquals;
    }
}
