package c_019_m;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 面试题（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到达5时，线程2给出提示并结束
 */
public class MyContainer1 {

    private List<Object> list = new ArrayList<>();

    public void add(Object ele) {
        list.add(ele);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {

        MyContainer1 container = new MyContainer1();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                container.add(new Object());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("add " + i);
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

此种方法是一种错误的实现：
add 0
add 1
add 2
add 3
add 4
add 5
add 6
add 7
add 8
add 9
.... t2 一直在运行，永远不结束


这是因为 container 对象的可见性问题

 */