package hw_5_2;

public class TestThread {
    public static void main(String[] args) {
        Thread one = new IntThread(1);
        Thread two = new IntThread(2);
        Thread three = new IntThread(3);

        one.start();
        two.start();
        three.start();
    }
}
