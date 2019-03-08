package c_013;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 关键字，使一个变量在多个线程间可见
 * volatile并不能保证多个线程共同修改running变量所带来的不一致的问题，也就是说volatile不能替代synchronized
 * 即 volatile只能保证可见性，不能保证原子性
 */
public class T {

    volatile int count = 0;
    /*AtomicInteger count = new AtomicInteger(0);*/
    
    /*synchronized*/ void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
            /*count.incrementAndGet();*/
        }
    }

    public static void main(String[] args) {
        // 创建一个10个线程的list，执行任务皆是 m方法
        T t = new T();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "t-" + i));
        }
        
        // 启动这10个线程
        threads.forEach(Thread::start);
        
        // join 到主线程，防止主线程先行结束
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 10个线程，每个线程执行10000次，结果应为 100000
        System.out.println(t.count);  // 所得结果并不为 100000，说明volatile 不保证原子性
    }

}


/*

解决方案：
1. 在方法上加上synchronized即可，synchronized既保证可见性，又保证原子性
2. 使用AtomicInteger代替int（AtomicXXX 代表此类中的所有方法都是原子操作，并且可以保证可见性）

 */