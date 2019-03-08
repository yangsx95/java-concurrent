package c_020;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock可以用于替代synchronized
 * 本例中由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 * 这里复习synchronized最原始的定义
 */
public class ReentrantLock1 {
    
    synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
    
    synchronized void m2() {
        System.out.println("m2...");
    }

    public static void main(String[] args) {
        ReentrantLock1 r1 = new ReentrantLock1();
        new Thread(r1::m1, "t1").start(); // m1 已经执行，被t1占有锁this
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2, "t2").start(); // 锁已经被其他线程占用，m1执行完毕后，不会执行
    }

}
