package com.threadDemo.thread;

import com.threadDemo.entity.AcountSynchronized;

/**
 * 消费者线程<br>
 * 
 * @author ping
 *
 */
public class DepositThread extends Thread {

	private AcountSynchronized acount;
	private double depositAmount;

	public DepositThread(String name,AcountSynchronized acount, double depositAmount) {
		super(name);
		this.acount = acount;
		this.depositAmount = depositAmount;
	}

	@Override
	public void run() {
		for(int i=0;i<100;i++){
			acount.depositBalance(depositAmount);
		}
	}

}
