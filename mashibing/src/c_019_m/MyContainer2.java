package c_019_m;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 面试题（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到达5时，线程2给出提示并结束
 */
public class MyContainer2 {

    private volatile List<Object> list = new ArrayList<>();

    public void add(Object ele) {
        list.add(ele);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        MyContainer2 container = new MyContainer2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                container.add(new Object());
                System.out.println("add " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (container.size() == 5) {
                    break;
                }
            }
            System.out.println("监测到容器长度为5，线程2立即退出");
        }, "t2").start();

    }

}

/*

添加 volatile ，使list发生变化时，主动通知其他线程，更新工作空间

上述代码，共有以下几个问题：
1. 不够精确，当container.size == 5 还未执行break时，有可能被其他线程抢占；或者 container.add() 之后，还未打印，就被 t2 判断size为5 直接推出了
2. 损耗性能，t2 线程，一直在走while循环，很浪费性能

 */