package hw_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void getName() {
        Student student = new Student("FMI1", "Ivan Petkov", 4.56);
        assertEquals("Ivan Petkov", student.getName());
    }

    @Test
    void getGpa() {
        Student student = new Student("FMI1", "Ivan Petkov", 4.56);
        assertEquals(4.56, student.getGpa());
    }

    @Test
    void getFacultyNumber() {
        Student student = new Student("FMI1", "Ivan Petkov", 4.56);
        assertEquals("FMI1", student.getFacultyNumber());
    }

    @Test
    void setName() {
        Student student = new Student("FMI1", "Ivan Petkov", 4.56);
        assertEquals("Ivan Petkov", student.getName());
        student.setName("Petko Ivanov");
        assertEquals("Petko Ivanov", student.getName());
    }

    @Test
    void setGpa() {
        Student student = new Student("FMI1", "Ivan Petkov", 4.56);
        assertEquals(4.56, student.getGpa());
        student.setGpa(5.20);
        assertEquals(5.20, student.getGpa());
    }

    @Test
    void setFacultyNumber() {
        Student student = new Student("FMI1", "Ivan Petkov", 4.56);
        assertEquals("FMI1", student.getFacultyNumber());
        student.setFacultyNumber("FMI15");
        assertEquals("FMI15", student.getFacultyNumber());
    }

    @Test
    void testToString() {
        Student student = new Student("FMI1", "Ivan Petkov", 4.56);
        assertEquals("FMI1\t4.56\tIvan Petkov", student.toString());
    }

    @Test
    void compareTo() {
        Student student1 = new Student("FMI1", "Ivan Petkov", 5.22);
        Student student2 = new Student("FMI2", "Ivana Ivanova", 5.22);
        Student student3 = new Student("FMI3", "Alex Ivanov", 4.40);
        Student student4 = new Student("FMI4", "Alex Ivanov", 4.60);

        assertEquals(0, student1.compareTo(student1));
        assertTrue(student1.compareTo(student2) < 0);
        assertTrue(student2.compareTo(student1) > 0);
        assertTrue(student3.compareTo(student4) > 0);
        assertTrue(student4.compareTo(student3) < 0);
    }
}