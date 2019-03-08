package c_025;

import java.util.concurrent.*;

/**
 * TransferQueue,
 * 拥有transfer方法，传输，当transfer一个元素时，如果有take方法阻塞等待获取元素，则不向队列中保存，直接给等待的take方法
 * 如果没有消费者线程，transfer线程将会阻塞
 * 
 * 情景：如果将元素放入队列，再拿给消费者线程，太慢了，如果需要的效率更高，可以使用TransferQueue来解决更高的并发
 * 
 */
public class T08_TransferQueue {

    public static void main(String[] args) {
        
        TransferQueue mq = new LinkedTransferQueue();
        
        // 先让消费者线程等待
        new Thread(() -> {
            try {
                System.out.println(mq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 再让生产者线程生产
        try {
            mq.transfer("aaa");  // put add 都不会阻塞，会添加到容器中，只有transfer才有此种功能（等待消费者直接获取），所以transfer是有容量的
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        /*new Thread(() -> {
            try {
                System.out.println(mq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }

}
