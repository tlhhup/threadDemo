package com.threadDemo.entity;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ʹ��Lock�Լ�condition��������������������
 * @author ping
 *
 */
public class AcountLock {

	private String acountName;
	private double balance;
	private final Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	// ��ʶ�Ƿ���Ǯ
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
					System.out.println(Thread.currentThread().getName() + "ȡǮ��" + acountBalance);
					this.balance -= acountBalance;
					System.out.println("�˻����Ϊ��" + this.balance);
				}
				// ��ʶΪûǮ��
				flag = false;
				// �����ڴ˶���������ϵȴ��������߳�
				condition.signalAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			lock.unlock();
		}
	}

	public void depositBalance(double acountBalance) {
		lock.lock();
		try {
			if (flag) {
				condition.await();
			} else {
				System.out.println(Thread.currentThread().getName() + "��Ǯ��" + acountBalance);
				this.balance += acountBalance;
				System.out.println("�˻����Ϊ��" + this.balance);
				// ��ʶΪ��Ǯ��
				flag = true;
				// �����ڴ˶���������ϵȴ��������߳�
				condition.signalAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			lock.unlock();
		}
	}

}
