package c_026;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledPool
 * Scheduled: 计划中的,定时的
 * 执行定时的任务,类似Delay, 可以替代Timer
 */
public class T10_ScheduledPool {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        // 使用固定的频率执行某个任务
        // 四个参数
        // command: 执行的任务
        // initialDelay: 第一次执行延时多久执行
        // period: 每隔多久执行一次这个任务
        // unit: 时间单位
        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);  // 每隔500ms打印一下线程名称
        // 线程执行1000ms,而每sleep 500 就要新启动一个线程
        // 上个线程未执行完毕,会启用新的线程执行
        // 如果线程池已满,只有延时
    } 
}
