# threadDemo
### 线程同步
	多个线程同时访问同一数据时有可能出现线程安全问题，及读取到的数据不正确
1. 采用线程同步的方式处理
	1. 同步代码块

			synchronized(监视器对象){
				同步代码块
			}
	2. 同步方法：在方法的返回值之前加上synchronized关键字
		1. 静态同步方法：该监视器的对象为class类本身
		2. 非静态同步方法：该监视器对象为当前类的实例对象
	3. 注意事项
		1. 要实现线程的同步，监视器对象必须相同
2. 采用Lock锁进制
	1. Lock是jdk1.5之后提供的线程同步的机制，Lock是控制多个线程对共享资源进行访问的工具。每次只能有一个线程对lock对象加锁，线程开始访问共享资源之前先获得lock对象。ReadWriteLock可允许对共享资源的并发访问，通常在线程安全控制中一般使用ReentrantLock(可重入)
	1. 在需要同步的地方调用lock()方法获取锁
	2. 在需要释放锁的地方调用unlock()方法--->通常放在finally语句块中

			Lock lock=new ReentrantLock();
			public void add(){
				//加锁
				lock.lock();
				try{
					//需要线程安全的代码
				}finally{
					//释放锁
					lock.unlock();
				}
			}
### 线程操作-->线程通信
1. 同步方式
	1. wait:阻塞当前线程，并释放监视器对象
	2. notify:唤醒在此对象监视器上等待的单个线程(如果所有多个则随机)。
	3. notifyAll：唤醒在此对象监视器上等待的所有线程。
4. 采用Lock锁及其提供的各种Condition
	1. await:类似wait方法，导致当前线程等待
	2. signal:唤醒在此Lock对象上等待的单个线程(多个则随机)
	3. signalAll:唤醒此Lock对象上等待的所有线程