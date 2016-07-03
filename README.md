# threadDemo
### 线程
1. 线程优先级：优先级0--->10,优先级越高越先执行
2. 前台进程：调用Thread的setDaemon(boolean)方法
### 线程同步
	多个线程同时访问同一数据时有可能出现线程安全问题，及读取到的数据不正确
1. 采用线程同步的方式处理-->隐式的加锁对象
	1. 同步代码块

			synchronized(监视器对象){
				同步代码块
			}
	2. 同步方法：在方法的返回值之前加上synchronized关键字
		1. 静态同步方法：该监视器的对象为class类本身
		2. 非静态同步方法：该监视器对象为当前类的实例对象
	3. 注意事项
		1. 要实现线程的同步，监视器对象必须相同
2. 采用Lock锁进制--->显示的指定的加锁的对象
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
4. 使用管道流
	1. 使用new创建管道流输入流和管道输出流对象
	2. 使用管道输入流或管道输出流对象的connect方法把两个输入流和输出流连接起来
	3. 将管道输入流、管道输出流分别传入两个线程
	4. 两个线程可以分别依赖各自的管道输入流、管道输出流进行通信

			PipedReader reader=null;
			PipedWriter writer=null;
			
			try {
				reader=new PipedReader();
				writer=new PipedWriter();
				//将输出流连接到输入流上
				writer.connect(reader);
				
				new WriterThread(writer).start();
				new ReaderThread(reader).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
###增强特性
1. Callable：视为Runable的增强类，其执行方法call具有返回值且可以抛出异常
2. Future：作为Callable的返回值
3. FutureTask：封装Callable对象的call方法的返回值
	1. 使用：
		1. 创建Callable的实现类
		2. 创建Callable实现类对象,使用FutureTask进行包装，该FutureTask封装了Callable对象的call方法的返回值
		3. 使用FutureTask对象作为Thread对象的target创建、并启动线程
		4. 调用FutureTask对象的方法来获取子线程执行后的返回值
5. 线程池
	1. Executors：用户创建线程池对象的一个工厂接口
	2. ExecutorService
	3. ThreadLocal：线程局部变量，在一个线程中可以共享该变量，对每个线程都创建新的副本数据
4. 线程安全的集合
	1. 通过Collections工具类提供的方法可以将非线程安全的集合转换为线程安全的集合
	2. 在jdk1.5之后在java.util.concurrent包中提供了线程安全的集合
		1. ConcurrentLinkedQueue：一个基于链接节点的无界线程安全队列。此队列按照 FIFO（先进先出）原则对元素进行排序，此队列不允许使用 null 元素。
		2. ConcurrentMap：默认情况支持16条线程并发写入