package c_020;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 和 synchronized 的区别
 * 
 * ReentrantLock 可以进行尝试锁定 tryLock 这样无法锁定、或者在指定时间内无法锁定，线程可以决定是否继续等待
 */
public class ReentrantLock3 {

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
        // 尝试获取锁，返回true拿到了
        if (lock.tryLock()) {
            // lock.tryLock(5, TimeUnit.SECONDS) // 等5s内还没拿到就返回false
            System.out.println("m2...");
        } else {
            System.out.println(" m2 没拿到锁");
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLock3 r1 = new ReentrantLock3();
        new Thread(r1::m1, "t1").start(); // m1 已经执行，被t1占有锁this
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2, "t2").start(); // 锁已经被其他线程占用，m1执行完毕后，不会执行
    }

}
