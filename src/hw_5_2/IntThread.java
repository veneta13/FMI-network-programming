package hw_5_2;

public class IntThread extends Thread {
    private final int n;

    IntThread(int n) {
        this.n = n;
    }

    public void run() {
        for (int i = 0; i < 15; i++) {
            System.out.println(n);
        }
    }
}
