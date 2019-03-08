package c_020;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock替代synchronized
 */
public class ReentrantLock2 {

    ReentrantLock lock = new ReentrantLock();

    void m1() {
        lock.lock(); // 相当于 synchronized
        try {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        } finally {
            lock.unlock(); // 使用完毕后，必须手动释放锁
            // 不同于synchronized，抛出异常后，不会自动释放锁，需要我们在finally中释放此锁
        }
    }

    void m2() {
        lock.lock(); // 相当于 synchronized
        try {
            System.out.println("m2...");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLock2 r1 = new ReentrantLock2();
        new Thread(r1::m1, "t1").start(); // m1 已经执行，被t1占有锁this
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2, "t2").start(); // 锁已经被其他线程占用，m1执行完毕后，不会执行
    }

}
