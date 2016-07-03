package com.threadDemo.entity;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock以及condition来处理生产者与消费者
 * @author ping
 *
 */
public class AcountLock {

	private String acountName;
	private double balance;
	private final Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	// 标识是否有钱
	private boolean flag = false;

	public AcountLock() {
	}

	public AcountLock(String acountName, double balance) {
		this.acountName = acountName;
		this.balance = balance;
	}

	public void drawBalance(double acountBalance) {
		lock.lock();
		try {
			if (!flag) {
				condition.await();
			} else {
				if (this.balance > acountBalance) {
					System.out.println(Thread.currentThread().getName() + "取钱：" + acountBalance);
					this.balance -= acountBalance;
					System.out.println("账户余额为：" + this.balance);
				}
				// 标识为没钱了
				flag = false;
				// 唤醒在此对象监视器上等待的所有线程
				condition.signalAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			lock.unlock();
		}
	}

	public void depositBalance(double acountBalance) {
		lock.lock();
		try {
			if (flag) {
				condition.await();
			} else {
				System.out.println(Thread.currentThread().getName() + "存钱：" + acountBalance);
				this.balance += acountBalance;
				System.out.println("账户余额为：" + this.balance);
				// 标识为有钱了
				flag = true;
				// 唤醒在此对象监视器上等待的所有线程
				condition.signalAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			lock.unlock();
		}
	}

}
