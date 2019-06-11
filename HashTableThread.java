package canessa.hashmaptable;

import java.util.Hashtable;
import java.util.concurrent.*;

/*
 * 
 */
public class HashTableThread implements Runnable {

	// **** members ****
	Hashtable<Integer, Integer>	ht;
	int 						begin;
	int 						end;
	CountDownLatch 				latch;
	
	// **** constructor ****
	public HashTableThread(Hashtable<Integer, Integer> ht, int begin, int end, CountDownLatch latch) {
		this.ht 	= ht;
		this.begin	= begin;
		this.end	= end;
		this.latch 	= latch;
	}

	// **** ****
	@Override
	public void run() {
		
		// **** do some work ****
		for (int i = begin; i < end; i++) {
			ht.put(i, i * 2);
		}
		
		// **** flag that this thread is done ****
		latch.countDown();
	}
	
}
