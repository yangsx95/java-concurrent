package c_021_m;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 经典面试题：写一个固定容量的容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 点：生产者消费者模式
 * <p>
 * 如果调用 get方法时，容器为空，get方法就需要阻塞等待
 * 如果调用 put方法时，容器满了，put方法就需要阻塞等待
 * <p>
 * 实现方式：
 * 1. wait/notify
 * 2. Condition
 * <p>
 * <p>
 * 使用Lock和Condition实现，可以精确唤醒某些线程
 */
public class MyContainer2<T> {

    private final LinkedList<T> list = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();


    public synchronized void put(T t) {
        lock.lock();
        try {
            while (MAX == count) {
                producer.await();
            }
            list.add(t);
            ++count;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public synchronized T get() {
        lock.lock();
        try {
            while (list.size() == 0) {
                consumer.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        T t = list.removeFirst();
        count--;
        producer.signalAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainer2<String> c = new MyContainer2<>();
        // 启动消费者线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            }, "c_" + i ).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 2; j++) {
                    c.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p_" + i).start();
        }
    }
}
