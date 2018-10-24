package scratch;

import java.util.Random;
import java.util.concurrent.Semaphore;

import util.Colors;

class Fork extends Semaphore {
	private static final long serialVersionUID = 1L;
	private static int nextId = 0;
	final int id;

	Fork() {
		super(1, true);
		id = nextId++;
	}

	@Override
	public String toString() {
		return "fork{" + id + "} ";
	}
}

class Philosopher implements Runnable {
	private static int nextId = 0;
	final int id;
	final Fork leftFork;
	final Fork rightFork;
	int eatCount = 0;
	private Random rand;
	private String color;
	private final int maxEatCount;

	Philosopher(Fork allForks[]) {
		this(allForks, 3);
	}

	Philosopher(Fork allForks[], int maxEatCount) {
		id = nextId++;
		leftFork = allForks[(id % allForks.length)];
		rightFork = allForks[((id + 1) % allForks.length)];
		rand = new Random();
		color = Colors.nextColor();
		this.maxEatCount = maxEatCount;
	}

	@Override
	public String toString() {
		return color + "philosopher{id=" + id + ", leftFork=" + leftFork.id + ", rightFork=" + rightFork.id + "} ";
	}

	public int getEatCount() {
		return eatCount;
	}

	@Override
	public void run() {
		while (eatCount < maxEatCount) {
			if (leftFork.tryAcquire()) {
				if (rightFork.tryAcquire()) {
					eat();
					rightFork.release();
				}
				leftFork.release();
				think();
			}
		}
		System.out.println(color + "*** " + this + "completes meal ***");
	}

	public void think() {
		try {
			System.out.println(color + "\t\t\t" + this + "thinking...");
			Thread.sleep(rand.nextInt(100));
		} catch (InterruptedException e) {
		}
	}

	public void eat() {
		try {
			System.out.println(color + this + "eating (count = " + (eatCount + 1) + ")");
			Thread.sleep(rand.nextInt(1000));
			System.out.println(color + this + "finished eating (count = " + (eatCount + 1) + ")");
			++eatCount;
		} catch (InterruptedException e) {
		}
	}
}

public class DiningPhilosophers {
	public static Philosopher[] createPhilosophers(int n, int numEat) {
		Fork[] forks = new Fork[n];

		for (int i = 0; i < forks.length; i++)
			forks[i] = new Fork();

		Philosopher[] philos = new Philosopher[n];

		for (int i = 0; i < philos.length; i++)
			philos[i] = new Philosopher(forks, numEat);

		return philos;
	}

	public static void main(String[] args) {
		for (Philosopher p : createPhilosophers(10, 5)) {
			new Thread(p).start();
		}
	}
}
