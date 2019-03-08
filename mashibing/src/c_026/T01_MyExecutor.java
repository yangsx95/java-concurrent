package c_026;

import java.util.concurrent.Executor;

/**
 * 作用, Executor 可以传入一个Runnable接口, runnable接口用于定义一项任务, 将任务传递给Executor后, 由 Executor.execute() 方法定义如何执行任务
 */
public class T01_MyExecutor implements Executor {


    public static void main(String[] args) {
        
    }
    
    @Override
    public void execute(Runnable command) {
        command.run();
        // new Thread(command).star(); 
    }
}
