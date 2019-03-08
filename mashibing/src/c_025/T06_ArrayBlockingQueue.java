package c_025;

import java.sql.Time;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 使用阻塞有界同步队列 ArrayBlockingQueue 完成生产者消费者模式
 */
public class T06_ArrayBlockingQueue {


    public static void main(String[] args) throws InterruptedException {

        BlockingQueue queue = new ArrayBlockingQueue<>(10);

        for (int i = 0; i < 10; i++) {
            queue.put("a" + i);
        }

        //queue.put("a11"); // 会阻塞
        //queue.add("a11"); // 会抛出异常
        //System.out.println(queue.offer("a11")); // 会返回false
        System.out.println(queue.offer("a11", 1, TimeUnit.SECONDS)); // 会等待1s,返回false, 如果1s内有空闲,则添加成功后返回true

    }

}
