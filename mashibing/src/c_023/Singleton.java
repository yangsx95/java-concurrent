package c_023;

/**
 * 线程安全的单例模式
 * <p>
 * 更好的是采用这种方式，既不用加锁，也能实现懒加载
 */
public class Singleton {
    
    private Singleton() {
    }
    
    private static class Inner {
        private static Singleton s = new Singleton();
    }

    public static Singleton getInstance() {
        return Inner.s;
    }
    
}
