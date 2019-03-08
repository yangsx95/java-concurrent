package c_001;

/**
 * synchronized 关键字
 * 对某个对象加锁
 */
public class T {

    private int count = 0;
    private final Object lock = new Object();
    
    public void m() {
        synchronized (lock) { // 任何线程要执行下面的代码，都必须先拿到lock锁，锁信息记录在堆内存对象中的，不是在栈引用中
            // 如果lock已经被锁定，其他线程再进入时，就会进行阻塞等待
            // 所以 synchronized 是互斥锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
        // 当代码块执行完毕后，锁就会被释放，然后被其他线程获取
    }
    
}
