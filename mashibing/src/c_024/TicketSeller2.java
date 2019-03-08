package c_024;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 * 
 * 分析下面的程序可能会产生哪些问题？ 
 * 
 * 使用Vector同步容器替换List容器
 */
public class TicketSeller2 {

    static Vector<String> tickets = new Vector<>();
    
    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("票-" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    // 将问题方法，睡1s
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了：" + tickets.remove(0));
                }
            }).start();
        }
    }
    
}

/*

依然不能解决问题，判断和操作依然是复合操作，不是原子操作
 */