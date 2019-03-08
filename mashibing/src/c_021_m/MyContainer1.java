package c_021_m;

import java.util.LinkedList;

/**
 * 经典面试题：写一个固定容量的容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 
 * 点：生产者消费者模式
 * 
 * 如果调用 get方法时，容器为空，get方法就需要阻塞等待
 * 如果调用 put方法时，容器满了，put方法就需要阻塞等待
 * 
 * 实现方式：
 * 1. wait/notify
 * 2. Condition
 */
public class MyContainer1<T> {
    
    private final LinkedList<T> list = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        while (MAX == count) { // 如果容量最大，释放锁等待    ///【这里为什么使用while，而不是使用if？？？】
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 否则 put 
        list.add(t);
        ++count;
        this.notifyAll(); // 通知消费者线程，可以消费了
        // 【这里为什么调用 notifyAll 而不是 notify ？】
    }

    public synchronized T get() {
        while (list.size() == 0) { // 如果容量为空，释放锁等待  
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 否则获取
        T t = list.removeFirst();
        count--;
        this.notifyAll(); // 通知生产者线程生产
        return t;
    }
}

/*

为什么使用while 而不是使用 if ？？？
在与wait()的配合中，百分之99的程序都是与while而不是if结合使用。
上述代码中，在容器已满的情况下，put方法会wait等待，当容器中的元素被消费者消费了一部分，就会唤醒所有put方法，
put方法会继续向下执行，直接执行list.add(t)，那么多个生产者线程执行list.add() 就有可能出现数据一致性的问题。
如果使用while则会循环判断，就避免了这些问题。

不是有锁吗？为什么会需要循环判断？
wait之后，锁就会失去，再次被唤醒时，并且得到锁之后，**是从list.add()开始执行的**，会无判断直接加入到容器中。


为什么调用 notifyAll 而不是 notify ？
因为notify有可能再次叫醒一个生产者线程


 */