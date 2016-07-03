package com.threadDemo.entity;

/**
 * ͨ��flag��ʶ�Լ�ͬ������������������������<br>
 * 1. ���������ڴ�Ǯ<br>
 * 2. ������ȡǮ
 * 
 * @author ping
 *
 */
public class AcountSynchronized {

	private String acountName;
	private double balance;
	// ��ʶ-->��Ǯ�ɹ�
	private boolean flag = false;

	public AcountSynchronized() {
	}

	public AcountSynchronized(String acountName, double balance) {
		this.acountName = acountName;
		this.balance = balance;
	}

	/**
	 * ������<br>
	 * ��ͬ���Ķ���Ϊ��ǰ�����ʵ����this
	 * 
	 * @param depositAcount
	 */
	public synchronized void depositBalance(double depositAcount) {
		try {
			if (flag) {// �����Ǯ�ˣ��Ͳ��ܴ�Ǯ����ȴ�����������
				wait();// ������ǰ�̣߳��ͷ�ͬ���ļ���������this
			} else {// ��Ǯ
				System.out.println(Thread.currentThread().getName() + "��Ǯ��" + depositAcount);
				this.balance += depositAcount;
				System.out.println("�˻����Ϊ��" + this.balance);
				// ��ʶΪ��Ǯ��
				flag = true;
				// �����ڴ˶���������ϵȴ��������߳�
				notifyAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������<br>
	 * ��ͬ���Ķ���Ϊ��ǰ�����ʵ����this
	 * 
	 * @param drawAccount
	 */
	public synchronized void drawBalance(double drawAccount) {
		try {
			// ���ûǮ
			if (!flag) {
				wait();
			} else {
				if (this.balance > drawAccount) {
					System.out.println(Thread.currentThread().getName() + "ȡǮ��" + drawAccount);
					this.balance -= drawAccount;
					System.out.println("�˻����Ϊ��" + this.balance);
				}
				// ��ʶΪûǮ��
				flag = false;
				// �����ڴ˶���������ϵȴ��������߳�
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
