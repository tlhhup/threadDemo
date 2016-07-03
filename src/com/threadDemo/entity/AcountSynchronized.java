package com.threadDemo.entity;

/**
 * 通过flag标识以及同步方法处理生产者与消费者<br>
 * 1. 生产者用于存钱<br>
 * 2. 消费者取钱
 * 
 * @author ping
 *
 */
public class AcountSynchronized {

	private String acountName;
	private double balance;
	// 标识-->存钱成功
	private boolean flag = false;

	public AcountSynchronized() {
	}

	public AcountSynchronized(String acountName, double balance) {
		this.acountName = acountName;
		this.balance = balance;
	}

	/**
	 * 生产者<br>
	 * 其同步的对象为当前对象的实例及this
	 * 
	 * @param depositAcount
	 */
	public synchronized void depositBalance(double depositAcount) {
		try {
			if (flag) {// 如果有钱了，就不能存钱，需等待消费者消费
				wait();// 阻塞当前线程，释放同步的监视器对象及this
			} else {// 存钱
				System.out.println(Thread.currentThread().getName() + "存钱：" + depositAcount);
				this.balance += depositAcount;
				System.out.println("账户余额为：" + this.balance);
				// 标识为有钱类
				flag = true;
				// 唤醒在此对象监视器上等待的所有线程
				notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 消费者<br>
	 * 其同步的对象为当前对象的实例及this
	 * 
	 * @param drawAccount
	 */
	public synchronized void drawBalance(double drawAccount) {
		try {
			// 如果没钱
			if (!flag) {
				wait();
			} else {
				if (this.balance > drawAccount) {
					System.out.println(Thread.currentThread().getName() + "取钱：" + drawAccount);
					this.balance -= drawAccount;
					System.out.println("账户余额为：" + this.balance);
				}
				// 标识为没钱了
				flag = false;
				// 唤醒在此对象监视器上等待的所有线程
				notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double getBalance() {
		return balance;
	}

}
