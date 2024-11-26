package hw_5_2;

public class TestThread2 {
    public static void main(String[] args) {
        Thread one = new IntThread(1);
        Thread two = new IntThread(2);
        Thread three = new IntThread(3);

        one.setPriority(Thread.MIN_PRIORITY);
        two.setPriority(Thread.MIN_PRIORITY + 1);
        three.setPriority(Thread.MIN_PRIORITY + 2);

        one.start();
        two.start();
        three.start();
    }
}
