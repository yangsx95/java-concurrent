package c_005;

/**
 * 分析下面程序的输出
 */
public class T implements Runnable{

    private int count = 10;
    
    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 5; i++) {
            new Thread(t).start();
        }
    }
}

/*

某次运行结果：
Thread-0 count = 7
Thread-4 count = 5
Thread-3 count = 6
Thread-2 count = 7
Thread-1 count = 7

线程重入的问题（线程执行过程中，被其他线程打断），因为 count-- + sout(count) 不是原子操作

解决办法，保证操作原子性，加上 synchronized 关键字

 */
