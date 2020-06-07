package me.daqiang.utils.block;

import me.daqiang.utils.ThinkJavaTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *  编程思想 - 第21章 - 21.4.3
 *
 *  验证不同类型的 阻塞， 是否能够顺利中断，并释放相关资源的问题
 *
 *  结论：
 *  1. sleep事件导致的线程阻塞， 会通过中断异常的方式，告诉调用者以改变执行策略
 *  2. IO 阻塞， 不会中断，
 *  3. 同步锁导致 其他线程阻塞，也不会中断
 * */


/**
 * @ClassName BlockTest
 * @Description TODO
 * @Author daqiang
 * @Date 2020/2/11 11:58 下午
 * @Version 1.0
 **/
public class BlockTest implements ThinkJavaTest {

    private static ExecutorService exec =
            Executors.newCachedThreadPool();

    @Override
    public void thinkJavaTest() throws InterruptedException {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlokced());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System.exit(0)");
        System.exit(0); // 由于最后2个任务中断失败
    }

    static void test(Runnable r) throws InterruptedException {
        Future<?> f = exec.submit(r); // 为了获取线程执行的上下文，并在这个基础上可以获得执行器（线程池）中某一个线程，进行中断操作， 如果使用 exec.shutDownNow() 可以中断所有在执行器内部的线程
        TimeUnit.MICROSECONDS.sleep(100);
        System.out.println("Interrupting " + r.getClass().getName());
        f.cancel(true); // Interrupts if running -- 在线程运行的时候，进行中断操作
        System.out.println("Interrupt sent to " + r.getClass().getName());
        System.out.println("");
    }
}
