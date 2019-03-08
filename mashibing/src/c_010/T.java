package c_010;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 是可重入锁
 * 子类调用父类的同步方法，是否也是可重入的？
 * 答：可重入的
 */
public class T {

    synchronized void m() {
        System.out.println("m start ");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end ");
    }

    public static void main(String[] args) {
        TT tt = new TT();
        tt.m();
    }
}

class TT extends T {
    @Override 
    synchronized void m() {
        System.out.println(" child m start ");
        super.m();
        System.out.println(" child m end ");
    }
}
