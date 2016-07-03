package com.threadDemo.test;

import com.threadDemo.entity.AcountSynchronized;
import com.threadDemo.thread.DepositThread;
import com.threadDemo.thread.DrawThread;

public class AcountTest {

	public static void main(String[] args) {
		AcountSynchronized acountSynchronized=new AcountSynchronized("张三", 0);
		new DrawThread("消费者",acountSynchronized,400).start();
		new DepositThread("生产者", acountSynchronized, 200).start();
	}
	
}
