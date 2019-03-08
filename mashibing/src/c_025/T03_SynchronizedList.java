package c_025;

import java.util.*;

/**
 * 将普通集合变为同步集合的工具方法
 */
public class T03_SynchronizedList {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        // 返回的实例，每个方法都加了一个互斥锁
        List<String> syncList = Collections.synchronizedList(list);
    }
    
}
