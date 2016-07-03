package com.threadDemo.thread;

import com.threadDemo.entity.AcountLock;

/**
 * 消费者线程<br>
 * 
 * @author ping
 *
 */
public class DrawLockThread extends Thread {

	private AcountLock acount;
	private double drawAmount;

	public DrawLockThread(String name,AcountLock acount, double drawAmount) {
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
