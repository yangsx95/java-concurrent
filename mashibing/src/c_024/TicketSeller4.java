package c_024;

import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 分析下面的程序可能会产生哪些问题？
 * <p>
 * 使用同步队列
 */
public class TicketSeller4 {

    static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) {
            queue.add("票-" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    String t = queue.poll(); // 取出头，拿不到就是空值
                    if (t == null) {
                        break;
                    }
                    System.out.println("销售了：" + t);
                }
            }).start();
        }
    }

}

/*

可以解决问题，效率可以

 */