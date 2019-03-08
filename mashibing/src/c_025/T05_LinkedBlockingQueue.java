package c_025;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 使用阻塞同步队列 LinkedBlockingQueue 完成生产者消费者模式
 * 使用场景较多。
 */
public class T05_LinkedBlockingQueue {


    public static void main(String[] args) {

        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // 启动生产者线程生产
        new Thread(() -> {
            for (int j = 0; j < 100; j++) {
                try {
                    queue.put("aaa" + j); // put 方法，给容器添加元素，如果容器已经满了，则会阻塞等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 启用消费者线程消费
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.out.println(Thread.currentThread().getName() + ":" + queue.take()); // 从队列中拿数据，如果空了，则会阻塞等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }

    }

}
