package me.daqiang.utils.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;


/**
 *  Java并发编程主要问题：
 *  1. 面临问题 ： 事件驱动、响应式、仿真、多任务
 *  2. 什么是并发？ 时间片
 *  3. 实现方式： 协作式、抢占式， 线程切换
 *  4. 代码：
 *  4.1 Runnable （普通接口而已）， 定义工作任务
 *  4.2 Thread （执行环境和主体）
 *  4.3 最基本的执行方式： 给Thread构造器，提交工作任务
 *  4.5 使用Executor
 *  4.6 sleep / setDaemon 设置后台线程，在线程启动前设置
 *  4.7 ThreadFactory
 *  4.8 ThreadPoolExecutor 执行器基类
 *
 *  5 线程的操作
 *  5.1 join , 在A线程中，调用B线程的join，可以挂起A线程，直到B线程执行结束。例外： join方法可以被中断叫停，即调用线程的 interrupt()
 *  5.2 后台线程，实现 UI响应式 方法
 *
 *
 *  6. 异常捕获
 *  6.1 在其他线程中运行抛出的异常，当前线程无法直接 try catch 捕获
 *  6.2 通过 给线程绑定一个异常捕获处理器 Thread.UncaughtExceptionHandler， 在线程初始化的时候 （利用 ThreadFactory）
 *  6.3 可以设置默认处理器 ， 表示未捕获异常的处理方式， setDefaultUncaughtExceptionHandler
 *
 *  7. 共享资源访问  (临界资源的访问)
 *  7.1 递增操作不是原子操作， 什么才是原子操作呢？
 *  7.2 要解决什么问题？ 在关键的地方，不能出现相同的资源被两个及以上的任务访问
 *  7.3 怎么解决？ 加锁
 *  7.4 序列化访问共享资源 ， 这种机制叫做 互斥量（mutex）
 *  7.5 等待锁的线程们，是不能确定谁下一个访问共享资源的， 可以通过 yield 和 setPriority 去告诉调度器， 作用效果跟JVM和平台有关
 *  7.6 共享资源一般是以对象形式存在的内存片段， 但也能使文件、输入/输出端口，或者打印机， 要控制共享资源的访问，得先把它包装进一个对象。
 *  7.6 锁方案一： synchronized, synchronized 针对所有访问共享资源的变量的方法 都需要加锁； synchronized 不能尝试着获取锁一段时间然后放弃他，要实现这些就需要Lock对象
 *      7.6.1 临界区 synchronized(obj) {} 针对方法内部的部分代码
 *  7.7 锁方案二： lock 对象: ReentrantLock,
 *
 *  程序员不应该对原子性抱有依赖，相反，应该保持对同步的敏感认知。
 *  7.8 原子性： 原子操作应该是不能被线程调度机制中断的操作，一旦操作开始，那么它一定在可能发生的"上下文切换"之前执行完毕；
 *  7.8.1 原子性可以应用于 除了 long和double之外的所有基本类型上的"简单操作"， 因为long和double的特殊构造--对64位的读写会分成两个32位的操作，导致可能会在上下文切换中丧失准确性。
 *  但是定义为  volatile ，就可以获得简单的复制和返回操作的原子性（因为什么呢？）。
 *  7.9 同步：就需要程序员主动加锁，
 *  7.10 可视性：对多核处理器，核内缓存对其他任务的可视性。
 *
 *  7.11 Java提供的原子类： AtomicInteger / AtomicLong / AtomicRefrence,
 *
 *  8.1 线程的本地存储： 解决资源共享问题的第二个方式， 自动化创建同一个变量的不同存储
 *  8.2
 *
 *
 *
 *
 * **/


/**
 * @ClassName Executor
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/8 9:57 下午
 * @Version 1.0
 **/
public class Executor {

    public static void testTheradWork() {
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new LiftOff());
//            t.setDaemon(true);
            t.start();
        }
        System.out.println("Waiting for LiftOff。。。");
    }

    // 无返回值方式 ， 通过 execute调用Runnable接口的方式
    public static void noReturnValueTask() {
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++)
            exec.execute(new LiftOff(i));
    }

    // 有返回值的并发方式， 通过 向 ExecutorService submit任务的方式， 获取Future对象
    public static void returnValueTest() {

        // 1. 线程池
//        ExecutorService exec = Executors.newCachedThreadPool();
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());

        // 2. 任务执行情况统计 列表
        ArrayList<Future<ArrayList<Integer>>> results = new ArrayList<>();
        // 3. 提交任务到线程池，执行
        for (int i = 0; i < 10; i++)
            results.add(exec.submit(new FibTask(i * 3)));
        System.out.println(results);
        // 4. 获取任务执行结果
        for (Future<ArrayList<Integer>> fs : results)
            try {
                System.out.println(fs.get()); /// ************* 阻塞等待 *************
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
                return;
            }

    }

    public static void fixedPool() {

        ExecutorService fixedExec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++)
            fixedExec.execute(new LiftOff());
        fixedExec.shutdown();

//        fixedExec.execute(new LiftOff());
    }

    public static void singleQueue() {
        ExecutorService singleExec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++)
            singleExec.execute(new LiftOff());
    }


    public static void joineThreadTest() {
        Sleeper
          sleeper = new Sleeper("Sleepy", 1500),
          grumpy = new Sleeper("Grumpy-截断", 1500);
        Joiner
          dopey = new Joiner("Dopey", sleeper),
          doc = new Joiner("Doc", grumpy);
        grumpy.interrupt();
    }

    public static void responsiveTest() {

        new ResponsiveUI();
        try {
            System.in.read();
        } catch (IOException e) {

        }
        System.out.println( ResponsiveUI.d);
    }


    public static void exceptionTest() {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread());
    }

    public static void sharedResAccess() {

//        EvenGenerator g = new EvenGenerator();
//        SynchronizedGenerator g = new SynchronizedGenerator();
        MutexEvenGenerator g = new MutexEvenGenerator();

        System.out.println("Press Control-C to exit.");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++)
            exec.execute(new EvenChecker(g, i));
    }

    public static void threadLocalTest() {

        ExecutorService exec =  Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Accessor(i));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
        }
        exec.shutdownNow(); // 退出任务的条件
    }
}




class SimpleThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;

    SimpleThread() {
        super(Integer.toString(++threadCount));
        start();
    }

    @Override
    public String toString() {
        return "#{" +
                getName() +
                "countDown=" + countDown +
                '}';
    }

    @Override
    public void run() {
//        super.run();
        while (true) {
            System.out.println(this);
            if (--countDown == 0) {
                return;
            }
        }
    }
}
