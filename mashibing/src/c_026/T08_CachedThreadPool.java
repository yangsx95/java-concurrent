package c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * CachedThreadPool 
 * 可缓存的线程
 * 当有个请求进入线程池内, 线程池将会启用一个线程
 * 当再次有个请求进入线程池内, 并且上个线程未结束, 仍然会启用一个线程
 * 当有线程执行完毕后,这个线程不会被清除, 而是被缓存,当有请求进入时, 直接使用缓存线程调用
 * 跟 fixedThreadPool 类似, 只不过没有上限(最多Integer最大值), 是一种弹性操作
 * 当线程一直不被使用, 缓存最多持续1分钟(AliveTime默认值),就会被线程池销毁
 * 
 * 内部是 DelayedWorkQueue
 */
public class T08_CachedThreadPool {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        // pool size 为0
        System.out.println(service); // java.util.concurrent.ThreadPoolExecutor@7f31245a[Running, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 0]cu'

        for (int i = 0; i < 2; i++) {
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        // pool size 变为2 
        System.out.println(service); // java.util.concurrent.ThreadPoolExecutor@7f31245a[Running, pool size = 2, active threads = 2, queued tasks = 0, completed tasks = 0]


        try {
            TimeUnit.SECONDS.sleep(80); // 最多持续1分钟,这里sleep80s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // pool size 变为0
        System.out.println(service); // java.util.concurrent.ThreadPoolExecutor@7f31245a[Running, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 2]

    } 
}
