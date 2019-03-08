package c_025;

import java.util.concurrent.*;

/**
 * DelayQueue,
 * 出队有个时间限制, 每个元素有一个等待时间, 可以按照等待时间排序元素
 * DelayQueue元素必须为 Delayed类型的,即必须设置元素的等待时间
 * 
 * 用途，定时执行任务
 */
public class T07_DelayQueue {

    public static void main(String[] args) throws InterruptedException {
        long timestamp = System.currentTimeMillis();
        MyTask myTask1 = new MyTask(timestamp + 1000); // 1s后执行
        MyTask myTask2 = new MyTask(timestamp + 2000);
        MyTask myTask3 = new MyTask(timestamp + 1500);
        MyTask myTask4 = new MyTask(timestamp + 2500);
        MyTask myTask5 = new MyTask(timestamp + 500);

        DelayQueue<MyTask> tasks = new DelayQueue<>();
        tasks.put(myTask1);
        tasks.put(myTask2);
        tasks.put(myTask3);
        tasks.put(myTask4);
        tasks.put(myTask5);

        System.out.println(tasks);  // 确实按照我们拍的顺序执行的

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.take());
        }
    }

    static class MyTask implements Delayed {
        private long runningTime;

        public MyTask(long runTime) {
            this.runningTime = runTime;
        }
        
        // 这是每个元素的等待时间, 越是后加入的元素,时间等待的越长
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        // 这是排序规律, 执行等待时间最短的排在上面
        @Override
        public int compareTo(Delayed o) {
            return (int) (o.getDelay(TimeUnit.MILLISECONDS) - this.getDelay(TimeUnit.MILLISECONDS));
        }
        
        @Override
        public String toString() {
            return runningTime + "";
        }
    }

}
