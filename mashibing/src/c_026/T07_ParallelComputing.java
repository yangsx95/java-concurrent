package c_026;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池的作用:并行计算
 * 计算 1-200000 之间的质数
 */
public class T07_ParallelComputing {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> result = getPrime(1, 20_0000);
        long end = System.currentTimeMillis();
        System.out.println(end - start); // 使用单线程计算的时间

        ExecutorService service = Executors.newFixedThreadPool(4);
        ComputeTask t1 = new ComputeTask(1, 8_0000);
        ComputeTask t2 = new ComputeTask(8_0001, 13_0000);
        ComputeTask t3 = new ComputeTask(13_0001, 17_0000);
        ComputeTask t4 = new ComputeTask(17_0001, 20_0000);  // 这里为什么不均分? 因为数字越大, 质数的数量就越多
        // 提交任务给ExecutorService执行
        Future<List<Integer>> f1 = service.submit(t1);
        Future<List<Integer>> f2 = service.submit(t2);
        Future<List<Integer>> f3 = service.submit(t3);
        Future<List<Integer>> f4 = service.submit(t4);
        // 执行开始
        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println(end - start);
        service.shutdown();
    }

    static class ComputeTask implements Callable<List<Integer>> {

        private int start, end;
        
        ComputeTask (int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public List<Integer> call() throws Exception {
            return getPrime(start, end);
        }
    }
    

    static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     * 返回指定范围的质数列表
     */
    static List<Integer> getPrime(int start, int end) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < end; i++) {
            if (isPrime(i)) list.add(i);
        }
        return list;
    }
}
