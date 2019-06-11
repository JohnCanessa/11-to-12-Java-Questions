package canessa.hashmaptable;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.*;


public class solution {
	
	// **** ****
	final static int MAX_KEY	= 11;
	
	
	/*
	 * Hashtable is thread-safe
	 * Hashtable does not allow null key or values
	 * The thread-safety of the Hashtable is achieved using internal synchronization,
	 * which makes it slower than HashMap.
	 */
	static void hashTableImpl() {
		
		Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();

		// **** populate the Hashtable ****
		for (int i = 0; i < MAX_KEY; i++) {
			
			// ???? ????
			System.out.println("hashTableImpl <<< i: " + i);
			
			// **** ****
			try {
				if (i == MAX_KEY / 3)
					ht.put(null, i * 2);
				else if(i == MAX_KEY / 2)
					ht.put(i, null);
				else
					ht.put(i, i * 2);
			} catch (Exception e) {
				System.err.println("hashTableImpl <<< EXCEPTION i: " + i + " " + e.getMessage());
			}
		}
		
		// **** display the Hashtable ****
		System.out.println("hashTableImpl <<< ht: " + ht.toString());
	}
	
	
	/*
	 * HashMap is not thread-safe (external synchronization)
	 * HashMap allows one null key and null values
	 */
	static void hasMapImpl() {
		
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		
		// **** populate the HashMap ****
		for (int i = 0; i < MAX_KEY; i++) {
			
			// ???? ????
			System.out.println("hasMapImpl <<< i: " + i);
			
			// **** ****
			try {
				if (i == MAX_KEY / 3)
					hm.put(null, i * 2);
				else if(i == MAX_KEY / 2)
					hm.put(i, null);
				else
					hm.put(i, i * 2);
			} catch (Exception e) {
				System.err.println("hasMapImpl <<< EXCEPTION i: " + i + " " + e.getMessage());
			}
		}
		
		// **** display the HashMap ****
		System.out.println("hasMapImpl <<< hm: " + hm.toString());
	}
	
	
	/*
	 * 
	 */
	static void hashTableThreads() {
		
		// **** ****
		Hashtable<Integer, Integer> ht = new Hashtable<Integer, Integer>();
		
		// **** ****
		CountDownLatch latch = new CountDownLatch(3);

		// **** ****
		long startTime = System.nanoTime();

		// **** create and start threads ****
		new Thread(new HashTableThread(ht,    0, 1000, latch)).start();
		new Thread(new HashTableThread(ht, 1000, 2000, latch)).start();
		new Thread(new HashTableThread(ht, 2000, 3000, latch)).start();
				
		// **** wait for all threads to finish ****
		try {
			latch.await();
		} catch (InterruptedException e) {
			System.err.println("hashTableThreads <<< EXCEPTION " + e.getMessage());
			e.printStackTrace();
		}
		
		// **** ****
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println("hashTableThreads <<< threads finished elapsedTime: " + elapsedTime + " ns");
		
		// **** display the Hashtable ****
		System.out.println("hashTableThreads <<< ht.size: " + ht.size());
		System.out.println("hashTableThreads <<<      ht: " + ht.toString());
	}
	
	
	/*
	 * 
	 */
	static void hashMapThreads() {
		
		// **** ****
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		
		// **** ****
		CountDownLatch latch = new CountDownLatch(3);

		// **** ****
		long startTime = System.nanoTime();
		
		// **** create and start threads ****
		new Thread(new HashMapThread(hm,    0, 1000, latch)).start();
		new Thread(new HashMapThread(hm, 1000, 2000, latch)).start();
		new Thread(new HashMapThread(hm, 2000, 3000, latch)).start();
		
		// **** wait for all threads to finish ****
		try {
			latch.await();
		} catch (InterruptedException e) {
			System.err.println("hashMapThreads <<< EXCEPTION " + e.getMessage());
			e.printStackTrace();
		}
		
		// **** ****
		long stopTime = System.nanoTime();
		long elapsedTime = stopTime - startTime;
		System.out.println("hashMapThreads <<< threads finished elapsedTime: " + elapsedTime + " ns");
		
		// **** display the HashMap ****
		System.out.println("hashMapThreads <<< hm.size: " + hm.size());
		System.out.println("hashMapThreads <<<      hm: " + hm.toString());
	}
	
	
	/*
	 * 
	 */
	public static void main(String[] args) {

		// **** Hashtable implementation ****
		hashTableImpl();
		System.out.println();
		
		// **** HashMap implementation ****
		hasMapImpl();
		System.out.println();

		// **** Hashtable thread(s) ****
		hashTableThreads();
		System.out.println();
		
		// **** *HashMap thread(s) ****
		hashMapThreads();
		System.out.println();
		
		// **** ****
		System.out.println("main <<< all done!!!");
	}

}
