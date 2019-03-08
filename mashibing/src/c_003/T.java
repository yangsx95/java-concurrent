package c_003;

/**
 * synchronized 关键字
 * synchronized 方法
 */
public class T {

    private int count = 10;

    public synchronized void m() { // 等同于 synchronized (this) { 
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
    
}
