package c_026;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadPoolExecutor
 * 线程池的实现原理，除了ForkJoinPool与WorkStealingPool线程池，其他线程池大部分线程池背后都是ThreadPoolExecutor
 * 
 * 自定义线程池，ThreadPoolExecutor
 */
public class T13_ThreadPoolExecutor {
    
    /*
    构造 ThreadPoolExecutor: 
    corePoolSize            线程池核心线程数，最小线程数
    maximumPoolSize         最大线程数
    keepAlive               线程空闲后存活时间， 0代表永远不会消失
    timeUnit                单位
    BlockingQueue workQueue 任务容器
    
    具体查看每个线程池
     */

    public static void main(String[] args) {
        
    }
}
