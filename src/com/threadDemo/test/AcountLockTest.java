package com.threadDemo.test;

import com.threadDemo.entity.AcountLock;
import com.threadDemo.thread.DepositLockThread;
import com.threadDemo.thread.DrawLockThread;

public class AcountLockTest {

	public static void main(String[] args) {
		AcountLock acountLock=new AcountLock("张三", 0);
		new DrawLockThread("消费者",acountLock,400).start();
		new DepositLockThread("生产者", acountLock, 200).start();
	}
	
}
