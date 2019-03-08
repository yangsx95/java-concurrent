package c_002;

/**
 * synchronized 关键字
 * 对this加锁
 * 
 * 每次使用锁都要newObject，比较麻烦，可以使用this代替object锁
 */
public class T {

    private int count = 10;
    
    public void m() {
        synchronized (this) { // 任何线程要执行下面的代码，必须先拿到this锁
            // synchronized 锁定的不是代码块，而是 this 对象
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }
}
