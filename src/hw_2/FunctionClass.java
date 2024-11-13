package hw_2;

import java.util.*;

public class FunctionClass {
    public static void main(String[] args) {
        List<Student> studentClass = new LinkedList<Student>();

        studentClass.add(new Student("FMI1", "Ivan Petkov", 4.56));
        studentClass.add(new Student("FMI2", "Yoana Ivanova", 5.67));
        studentClass.add(new Student("FMI3", "Petar Dimitrov", 5.23));
        studentClass.add(new Student("FMI4", "Kalina Stoilova", 5.34));
        studentClass.add(new Student("FMI5", "Yordan Georgiev", 5.67));

        Collections.sort(studentClass);

        for (Student student : studentClass) {
            System.out.println(student);
        }
    }
}
