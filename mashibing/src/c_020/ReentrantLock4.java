package c_020;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 和 synchronized 的区别
 * 
 * ReentrantLock 可以调用 lockInterruptibly方法，可以对线程interrupt方法做出响应
 * 在一个线程等待锁的过程中，可以被interrupt方法打断等待。
 */
public class ReentrantLock4 {

    

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);  // 线程一直占用锁
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            
        }, "t1").start();

        Thread t2 = new Thread(() -> {

            try {
                lock.lockInterruptibly(); // t2 尝试获取锁
                System.out.println("t2 start");
            } catch (InterruptedException e) {
                System.out.println("t2 等待中被打断");
            } finally {
                lock.unlock(); // 没有锁定进行unlock就会抛出 IllegalMonitorStateException 
            }
        }, "t2");
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打断线程2的等待
        t2.interrupt();
        
    }

}
