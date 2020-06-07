package me.daqiang.utils.thread;

import java.util.concurrent.ThreadFactory;

/**
 * @ClassName ExceptionThread
 * @Description TODO
 * @Author dongfuqiang
 * @Date 2020/2/9 11:51 下午
 * @Version 1.0
 **/
public class ExceptionThread implements Runnable {

    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println(t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread.");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh " + t.getUncaughtExceptionHandler());
        return t;
    }
}
