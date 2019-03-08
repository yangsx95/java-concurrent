package c_026;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPool 
 * 线程池
 */
public class T05_ThreadPool {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(5); // 固定长度的线程池
        for (int i = 0; i < 6; i++) { // 执行六个任务,  在只有五个固定容量的线程池中
            service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(service); // [Running, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
        // 内部一般是BlockingQueue
        // pool size =  5  线程池的容量
        // active thread = 5 激活的线程队列长度
        // queued tasks = 1 等待处理任务长度
        // completed task = 0 完成执行的任务数量
        
        // 关闭线程池
        service.shutdown(); // 未执行完毕,不会停止,只会进入停止中状态
        System.out.println(service.isTerminated()); // false 是否结束
        System.out.println(service.isShutdown()); // true 状态是否关闭
        System.out.println(service);  //java.util.concurrent.ThreadPoolExecutor@3b9a45b3[Shutting down, pool size = 5, active threads = 5, queued tasks = 1, completed tasks = 0]
        

        TimeUnit.SECONDS.sleep(5); // 5s 后肯定执行完成了

        System.out.println(service.isTerminated()); // true 
        System.out.println(service.isShutdown()); // true
        System.out.println(service); // java.util.concurrent.ThreadPoolExecutor@3b9a45b3[Terminated, pool size = 0, active threads = 0, queued tasks = 0, completed tasks = 6]

        
        // 线程池中维护了两个任务队列
        // 1. 未执行的任务队列
        // 2. 已执行的任务队列
    }
    
    
}
