package c_016;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * synchronized 优化
 *  同步代码块中的语句越少越好
 * 
 * 比较m1和m2
 */
public class T {

    int count = 0;
    
    synchronized void m1() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 业务逻辑中，只有下面这句代码需要 sync， 这时不应该给整个方法上锁
        count++;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    void m2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 业务逻辑中，只有下面这句需要 sync，这时不应该给整个方法上锁
        // 采用细粒度的锁，可以使线程争用时间变短，从而提高效率
        synchronized (this) {
            count++;
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
