package canessa.hashmaptable;

import java.util.HashMap;
import java.util.concurrent.*;


/*
 * 
 */
public class HashMapThread implements Runnable {

	// **** members ****
	HashMap<Integer, Integer>	hm;
	int 						begin;
	int 						end;
	CountDownLatch 				latch;	
	
	// **** constructor ****
	public HashMapThread(HashMap<Integer, Integer> hm, int begin, int end, CountDownLatch latch) {
		this.hm 	= hm;
		this.begin	= begin;
		this.end	= end;
		this.latch 	= latch;
	}
	
	// **** ****
	@Override
	public void run() {
		
		// **** do some work ****
		for (int i = begin; i < end; i++) {
			hm.put(i, i * 2);
		}
		
		// **** flag that this thread is done ****
		latch.countDown();
	}

}
