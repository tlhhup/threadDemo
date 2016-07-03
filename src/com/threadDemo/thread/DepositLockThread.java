package com.threadDemo.thread;

import com.threadDemo.entity.AcountLock;

/**
 * 消费者线程<br>
 * 
 * @author ping
 *
 */
public class DepositLockThread extends Thread {

	private AcountLock acount;
	private double depositAmount;

	public DepositLockThread(String name,AcountLock acount, double depositAmount) {
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
