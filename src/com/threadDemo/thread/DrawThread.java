package com.threadDemo.thread;

import com.threadDemo.entity.AcountSynchronized;

/**
 * 消费者线程<br>
 * 
 * @author ping
 *
 */
public class DrawThread extends Thread {

	private AcountSynchronized acount;
	private double drawAmount;

	public DrawThread(String name,AcountSynchronized acount, double drawAmount) {
		super(name);
		this.acount = acount;
		this.drawAmount = drawAmount;
	}

	@Override
	public void run() {
		for(int i=0;i<100;i++){
			acount.drawBalance(drawAmount);
		}
	}

}
