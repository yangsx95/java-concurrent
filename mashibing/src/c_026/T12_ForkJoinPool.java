package c_026;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

/**
 * T12_ForkJoinPool  分而治之
 * Fork: 分叉
 * Join: 合并
 * 
 * 将一个任务拆分多个任务执行(可以无限切分),然后将结果合并
 * 
 * 比如大量的并行计算, 如下: 求100_0000个数字之和, 使用多线程
 */
public class T12_ForkJoinPool {

    static int[] nums = new int[100_0000];
    static final int MAX_NUM = 5_0000; // 每个线程最多可以运行5万个数字相加
    static Random random = new Random();
    
    // 初始化这100_000个数字, 每个数字范围在100之内
    static {
        
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }
        // 所有数字和, 事先计算:
        //System.out.println(Arrays.stream(nums).sum()); // 使用单线程stream api 进行求和
    }
    
    /**
     * RecursiveAction: 递归操作 没有返回值
     * RecursiveTask: 递归操作,有返回值
     */
    static class AddTask extends RecursiveAction {
        
        int start, end;
        
        AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            
            // 进行计算
            // 如果计算的数的和的范围 小于 MAX_NUM, 进行计算,否则进行 fork 
            if (end - start <= MAX_NUM) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("sum = " + sum);
            } else {
                int middle = (end - start) / 2;
                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }

    static class AddTask2 extends RecursiveTask<Long> {

        int start, end;
        
        AddTask2(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        protected Long compute() {
            // 进行计算
            // 如果计算的数的和的范围 小于 MAX_NUM, 进行计算,否则进行 fork 
            if (end - start <= MAX_NUM) {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            } else {
                int middle = start + (end - start) / 2; // 注意这里，如果有问题，会抛出java.lang.NoClassDefFoundError: Could not initialize class java.util.concurrent.locks.AbstractQueuedSynchronizer$Node 异常
                AddTask2 subTask1 = new AddTask2(start, middle);
                AddTask2 subTask2 = new AddTask2(middle, end);
                subTask1.fork();
                subTask2.fork();
                return subTask1.join() + subTask2.join();
            }
        }
    }

    // 运行
    public static void main(String[] args) throws IOException {
        ForkJoinPool fjp = new ForkJoinPool();
        AddTask2 task = new AddTask2(0, nums.length);
        fjp.execute(task);
        System.out.println(task.join());
        
        //System.in.read();
    }

}
