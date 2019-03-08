package a_006;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 证明AtomicXX类比synchronized更高效
 */
public class T {

    AtomicBoolean running = new AtomicBoolean(true);

    void m() {
        System.out.println(" m start ");
        while (running.get());
        System.out.println(" m end ");
    }

    public static void main(String[] args) {
        T t = new T();
        Thread thread = new Thread(t::m);
        thread.start();
        
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.running.set(false);
    }
}
