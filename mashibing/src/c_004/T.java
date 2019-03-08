package c_004;

/**
 * synchronized 关键字
 * synchronized 静态方法
 * 
 * 锁定静态方法，其实锁定的是 java.lang.Class 对象。
 */
public class T {

    private static int count = 10;

    public static synchronized void m() { // 等同于 synchronized (c_004.T.class) { 
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
    
}
