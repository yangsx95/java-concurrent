package c_026;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * Callable
 * 类似Runnable, 执行Runnable任务时,实际调用的是run方法
 * 执行Callable任务时,实际调用的是 call方法
 * 
 * 区别:
 *  1. callable 有返回值,线程运行结束后需要返回值,则需要callable
 *  2. callable可以抛出异常,而Runnable不能抛出异常,必须自己处理
 */
public class T03_Callable {

    Callable callable;

}
