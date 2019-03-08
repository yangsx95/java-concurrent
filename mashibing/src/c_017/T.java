package c_017;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某个对象o，如果o属性发生变化，不影响锁的使用
 * 但是如果o编程另一个对象，则锁定的对象发生变化，
 * 所以锁对象通常要设置为 final类型，保证引用不可以变
 */
public class T {

    Object o = new Object();
    
    void m() {
        synchronized (o) {
            while (true) {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "线程1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(t::m, "线程2");
        t.o = new Object(); // 改变锁引用, 线程2也有机会运行，否则一直都是线程1 运行
        thread2.start();
    }

}
