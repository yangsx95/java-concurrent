package c_026;

import java.util.concurrent.*;

/**
 * Future 未来的执行结果 
 */
public class T06_Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 未来任务, 既是Runnable 也是 Future
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 100;
        });
        new Thread(task).start();

        System.out.println(task.get()); // 阻塞等待任务执行完成, 获取到返回值

        System.out.println("-------------------------------");
        
        //********************************
        // 使用ExecutorService的submit替代FutureTask
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> result = service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
        System.out.println(result.isDone()); // false 执行未完毕
        System.out.println(result.get()); // 1 
        System.out.println(result.isDone()); // true  执行已完毕
        System.out.println(result.get()); // 一直等待
        System.out.println(service.shutdownNow()); // 立即等待
        
    }
}
