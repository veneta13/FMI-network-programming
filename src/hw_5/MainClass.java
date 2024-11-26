package hw_5;

public class MainClass{
	String[] first = {"one", "two", "three", "four", 
		"five", "six", "seven", "eight", "nine", "ten"};
	String[] second = {"blue", "red", "grey", "green", 
		"yellow", "orange", "pink", "black", "white"};

	public static void main(String[] args) {
		MainClass mc = new MainClass();
		RunnableClass s1 = new RunnableClass(mc.first);
		RunnableClass s2 = new RunnableClass(mc.second);

		Thread t1 = new Thread(s1);
		Thread t2 = new Thread(s2);

		t1.start();
		t2.start();
	}
}
