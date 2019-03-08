package c_008;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁，而对业务读方法不加锁，
 * 容易出现 脏读问题
 * 
 * 因为，在执行写的过程中，因为读操作没有加锁，所以读会读取到写为改完的脏数据
 * 解决办法，给读写都加锁
 */
public class Account {

    String name; // 银行账户名称
    double balance; // 银行账余额

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public /*synchronized*/ double getBalance() {
        return this.balance;
    }

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(() -> a.set("张三", 100.0)).start();
        System.out.println(a.getBalance()); // 0.0 
        try {
            TimeUnit.SECONDS.sleep(3); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance()); // 100.0
    }
}
