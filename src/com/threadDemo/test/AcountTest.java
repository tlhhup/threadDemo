package com.threadDemo.test;

import com.threadDemo.entity.AcountSynchronized;
import com.threadDemo.thread.DepositThread;
import com.threadDemo.thread.DrawThread;

public class AcountTest {

	public static void main(String[] args) {
		AcountSynchronized acountSynchronized=new AcountSynchronized("����", 0);
		new DrawThread("������",acountSynchronized,400).start();
		new DepositThread("������", acountSynchronized, 200).start();
	}
	
}
