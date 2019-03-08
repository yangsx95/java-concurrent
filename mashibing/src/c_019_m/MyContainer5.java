package c_019_m;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 面试题（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到达5时，线程2给出提示并结束
 */
public class MyContainer5 {

    private volatile List<Object> list = new ArrayList<>();

    public void add(Object ele) {
        list.add(ele);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        MyContainer5 container = new MyContainer5();

        // Count down 往下数  Latch 门闩
        // 门闩不能保证可见性，不是一种同步方式，只是一种线程通信方式，保证不了可见性
        // 门闩的等待，不会持有任何锁
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 启动");
            if (container.size() != 5) {
                try {
                    latch.await();
                    // 指定等待时间
                    //latch.await(5000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("监测到容器长度为5，线程2立即退出");
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                container.add(new Object());
                System.out.println("add " + i);
                // 当长度为5时，撤掉一个门闩，此时门闩为0，门会打开，即t2会执行
                if (container.size() == 5) {
                    latch.countDown();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
    }

}

/*

使用CountDownLatch实现（最简单的方式）

Latch：门闩


使用Latch替代 wait notify来进行通信
好处是，通信简单，同时也可以指定等待时间
使用await和countDown 方法替代 wait 和 notify
CountDownLatch不涉及锁定，当count值为0时，当前线程继续运行
当不涉及同步，只涉及线程通信的时候，用synchronized + wait + notify 就显得太重了
 */