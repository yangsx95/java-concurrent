package a_007;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 写一个程序证明AtomicXXX类多个方法不构成原子性
 */
public class T {

    AtomicInteger count = new AtomicInteger(0);

    int m() {
        count.addAndGet(1);
        count.addAndGet(2);
        count.addAndGet(3);
        count.addAndGet(4);
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        T t = new T();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> System.out.println(t.m())
            ));
        }
        
        threads.forEach(Thread::start);
    }
}

// 跑出的结果始终是对的 ~~~