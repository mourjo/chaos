package scratch;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import util.Colors;

class Producer implements Runnable {
	private ArrayBlockingQueue<Integer> q;
	private String color;
	private int delay;

	Producer(ArrayBlockingQueue<Integer> q, String color, int delay) {
		this.q = q;
		this.color = color;
		this.delay = delay;
	}

	@Override
	public void run() {
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			try {
				int x = r.nextInt(1000);
				q.put(x);
				System.out.println(color + "Produced: " + x);
				Thread.sleep(r.nextInt(delay));
			} catch (InterruptedException e) {
			}
		}
		System.out.println(color + "*** Finished producing ***");
	}
}

class Consumer implements Runnable {
	private ArrayBlockingQueue<Integer> q;
	private String color;
	private int delay;

	Consumer(ArrayBlockingQueue<Integer> q, String color, int delay) {
		this.q = q;
		this.color = color;
		this.delay = delay;
	}

	@Override
	public void run() {
		Random r = new Random();
		while (true) {
			try {
				Thread.sleep(r.nextInt(delay));

				int k = q.take();
				if (k == Integer.MIN_VALUE) {
					q.put(k);
					break;
				} else
					System.out.println(color + "\t\t\tConsumed: " + k);

			} catch (InterruptedException e) {
			}
		}
		System.out.println(color + "\t\t\t***Finished consuming***");
	}
}

public class ProducerConsumerBlockingQueue {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ArrayBlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(3);

		ExecutorService e = Executors.newFixedThreadPool(10);

		e.submit(new Consumer(q, Colors.nextColor(), 100));
		e.submit(new Consumer(q, Colors.nextColor(), 200));
		e.submit(new Consumer(q, Colors.nextColor(), 300));
		e.submit(new Consumer(q, Colors.nextColor(), 400));

		Future<?> producer1 = e.submit(new Producer(q, Colors.nextColor(), 100));
		Future<?> producer2 = e.submit(new Producer(q, Colors.nextColor(), 150));
		Future<?> producer3 = e.submit(new Producer(q, Colors.nextColor(), 200));

		e.shutdown();

		producer1.get();
		producer2.get();
		producer3.get();

		q.put(Integer.MIN_VALUE);

		System.out.println(Colors.ANSI_RESET + "Main thread exits...");
	}
}