package hw_5;

public class RunnableClass implements Runnable {
	private String[] messages;

	RunnableClass(String[] messages) {
        this.messages = messages;
    }

	@Override
	public void run() {
        for (String message : messages) {
			System.out.println(message);
		}
	}
}

