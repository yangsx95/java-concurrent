package c_026;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * ExecutorService
 * 是Executor的服务, service 一般都是一些后台线程, 跑在后台提供服务, 这里代表 执行器服务
 * ExecutorService就是这种线程, 启动后一直在后台等待任务扔到容器中执行.
 * 
 * Future submit(Runnable): 提交Runnable任务以执行并返回表示该任务的Future。
 * Future submit(Runnable, result): 
 * Future submit(Callable): 提交一个有返回值的callable任务,并将返回值作为future对象包装返回
 *
 * execute 和 submit 的区别:
 * 没有本质区别,只不过execute只能执行Runnable接口
 */
public class T02_MyExecutorService  {

    ExecutorService executorService = null;
}
