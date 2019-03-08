package c_020;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 和 synchronized 的区别
 * <p>
 * ReentrantLock 可以指定为公平锁，synchronized 是不公平锁
 * 公平锁，先获取锁的人，在锁被释放时，优先获得锁
 * 不公平锁，无论先后，线程调度器将会随机给某个线程锁，不用计算线程时序，效率较高
 */
public class ReentrantLock5 extends Thread {

    private static ReentrantLock lock = new ReentrantLock(true);// 指定锁为公平锁

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取锁");
            } finally {
                lock.unlock(); // 公平锁 t1 unlock 后，等待时间长的一定是 t2 所以下次一定是 t2 执行
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 t1 = new ReentrantLock5();
        ReentrantLock5 t2 = new ReentrantLock5();
        t1.start();
        t2.start();
    }
}
