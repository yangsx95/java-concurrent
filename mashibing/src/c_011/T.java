package c_011;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 代码块中，如果发生异常，锁会被释放
 * 
 * 在并发处理过程中，有异常要多加小心，不然可能发生数据不一致的情况。
 * 比如，在一个web app处理过程中，多个servlet线程共同访问同一资源，这时如果异常处理不合适，
 * 第一个线程抛出异常，其他线程就会进入同步代码区，有可能访问到异常产生的数据。
 * 因此要非常小心处理同步业务员逻辑中的异常。
 */
public class T {

    int count = 0;
    
    synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count=" + count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count == 5) {  // 当count == 5 时，synchronized代码块会抛出异常
                int i = 1 / 0; 
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };
        new Thread(r, "t1").start(); // 执行到第5秒时，抛出 ArithmeticException 
        // 如果抛出异常后，t2 会继续执行，就代表t2拿到了锁，即t1在抛出异常后释放了锁
        
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(r, "t2").start();
    }

}