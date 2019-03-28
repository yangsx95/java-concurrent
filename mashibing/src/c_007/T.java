package c_007;

import java.util.concurrent.TimeUnit;

/**
 * 同步方法和非同步方法是否可以同时调用？
 * 答：肯定可以
 */
public class T {
    
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }
    
    public void m2() {
        System.out.println(Thread.currentThread().getName() + " m2 start");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 end");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m1).start();
        new Thread(t::m2).start();
    }
}
