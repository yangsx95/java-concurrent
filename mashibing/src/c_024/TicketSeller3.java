package c_024;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * <p>
 * 分析下面的程序可能会产生哪些问题？
 * <p>
 * 使用synchronized
 */
public class TicketSeller3 {

    static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票-" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    // sychronized 保证了原子性
                    synchronized (tickets) {
                        System.out.println("销售了：" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }

}

/*

可以解决问题，但是效率不高

 */