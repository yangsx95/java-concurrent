package c_009;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 是可重入锁
 * 即一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请时仍然会得到该对象的锁
 */
public class T {

    synchronized void m1() {
        System.out.println("m1 start ");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
    }

    synchronized void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(" m2"); // 这句话会打印，调用m2时，不会发生死锁
    }
}
