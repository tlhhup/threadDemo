package com.threadDemo.test;

import com.threadDemo.entity.AcountLock;
import com.threadDemo.thread.DepositLockThread;
import com.threadDemo.thread.DrawLockThread;

public class AcountLockTest {

	public static void main(String[] args) {
		AcountLock acountLock=new AcountLock("����", 0);
		new DrawLockThread("������",acountLock,400).start();
		new DepositLockThread("������", acountLock, 200).start();
	}
	
}
